package tools;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import net.dv8tion.jda.api.EmbedBuilder;

public class Dictionary {
	private static Dictionary instance;
	private String word;

	private Dictionary() {
		
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public static Dictionary getInstance() {
		if(instance == null) {
			instance = new Dictionary();
		} 
		return instance;
	}
	
	public String getResponse() {
		int status = 0;
		String urlStr = "https://api.dictionaryapi.dev/api/v2/entries/en/?";
		String correctUrlStr = Translator.replacelinkCharacters(urlStr, word);
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();

		String responseContentString = null;
		HttpURLConnection connection = null;

		try {
			// setup
			URL url = new URL(correctUrlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			status = connection.getResponseCode();

			if (status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			responseContentString = responseContent.toString();

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		return responseContentString;
	}

	public EmbedBuilder extractDataToEmbed(String responseBody) {
		EmbedBuilder embed = new EmbedBuilder();
		JSONArray albums = new JSONArray(responseBody);
		String word = albums.getJSONObject(0).getString("word");
		embed.setTitle(word);
		embed.setColor(new Color(0xf7cac9));

		for (int i = 0; i < albums.length(); i++) {
			JSONObject album = albums.getJSONObject(i);

			JSONArray meanings = album.getJSONArray("meanings");
			
			for(int j=0; j<meanings.length(); j++) {
				JSONObject everythingInMeaning = meanings.getJSONObject(j);
				String everyPartOfSpeech = everythingInMeaning.get("partOfSpeech").toString();
				
				JSONArray everythingInDefinitions = everythingInMeaning.getJSONArray("definitions");
				embed.setDescription("__**Part of speech:**__ \n" + "‣" + everyPartOfSpeech + "\n\n");
				
				//definition
				embed.appendDescription("__**Definition:**__ \n");
				for(int k=0; k<everythingInDefinitions.length(); k++) {
					String definition = everythingInDefinitions.getJSONObject(k).get("definition").toString();
					definition = definition.replace("â€™", "'");
					embed.appendDescription("‣" + definition + "\n");
				}
				embed.appendDescription("\n");
				
				//synonyms
				embed.appendDescription("__**Synonyms:**__" + "\n" + "‣");
				for(int l=0; l<everythingInDefinitions.length(); l++) {
					String synonyms = everythingInDefinitions.getJSONObject(l).get("synonyms").toString();
					synonyms = synonyms.replace("[]", "");
					synonyms = synonyms.replace("[", "");
					synonyms = synonyms.replace("]", "");
					synonyms = synonyms.replace("â€™\"", "'");
					//System.out.print(synonyms);
					embed.appendDescription(synonyms);
					
				}
				embed.appendDescription("\n\n");
				
				//antonyms
				embed.appendDescription("__**Antonyms:**__" + "\n" + "‣");
				for(int m=0; m<everythingInDefinitions.length(); m++) {
					String antonyms = everythingInDefinitions.getJSONObject(m).get("antonyms").toString();
					antonyms = antonyms.replace("[]", "");
					antonyms = antonyms.replace("[", "");
					antonyms = antonyms.replace("]", "");
					antonyms = antonyms.replace("â€™\"", "'");
					
					embed.appendDescription(antonyms);
				}
				
				embed.appendDescription("\n\n");
				
				meanings.getJSONObject(j);
				
			}

		}
		//System.out.println("");
		embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");
		return embed;
	}

}
