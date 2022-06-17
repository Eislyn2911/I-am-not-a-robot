package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
	private static Translator instance;
	private String langFrom = "";
	private String langTo;
	private String text;
	
	private Translator() {
		
	}

	public String getLangFrom() {
		return langFrom;
	}

	public void setLangFrom(String langFrom) {
		this.langFrom = langFrom;
	}

	public String getLangTo() {
		return langTo;
	}

	public void setLangTo(String langTo) {
		this.langTo = langTo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static Translator getInstance() {
		if(instance == null) {
			instance = new Translator();
		}
		return instance;
	}
	
	public String translate() throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbxA9Hn4G2jFfHJbzjD91Yo64rZAJ19i8FHSQLtTIqOBTUWEabXItuS_YSkLsE9qddka/exec" + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo +"&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
	
	public String replaceHTMLCharacters(String s) throws UnsupportedEncodingException {
		if (s == null || s.length() == 0) {
			return s;
		}

		s = s.replace("&nbsp;", " ");
		s = s.replace("&quot;", "\"");
		s = s.replace("&apos;", "'");
		s = s.replace("&#39;", "'");
		s = s.replace("&lt;", "<");
		s = s.replace("&gt;", ">");
		s = s.replace("&amp;", "&");

		// whitespace patterns
		String zeroOrMoreWhitespaces = "\\s*?";
		String oneOrMoreWhitespaces = "\\s+?";

		// replace <br/> by \n
		s = s.replaceAll("<" + zeroOrMoreWhitespaces + "br" + zeroOrMoreWhitespaces + "/" + zeroOrMoreWhitespaces + ">",
				"\n");
		// replace HTML-tabs by \t
		s = s.replaceAll(
				"<" + zeroOrMoreWhitespaces + "span" + oneOrMoreWhitespaces + "style" + zeroOrMoreWhitespaces + "="
						+ zeroOrMoreWhitespaces + "\"white-space:pre\"" + zeroOrMoreWhitespaces + ">&#9;<"
						+ zeroOrMoreWhitespaces + "/" + zeroOrMoreWhitespaces + "span" + zeroOrMoreWhitespaces + ">",
				"\t");

		return s;
	}

	public static int length(String s) {
		if (s == null) {
			return 0;
		} else {
			return s.length();
		}
	}

	/**
	 * replace plain text without using regex
	 */
	public static String replace(String s, String sOld, String sNew) {
		sNew = (sNew == null ? "" : sNew);
		if (s == null || sOld == null) {
			return s;
		}
		return s.replace(sOld, sNew);
	}
	
	public static String replacelinkCharacters(String link, String word) {
		if (link == null || link.length() == 0) {
			return link;
		}

		link = link.replace("?", word);

		// whitespace patterns
		String zeroOrMoreWhitespaces = "\\s*?";
		String oneOrMoreWhitespaces = "\\s+?";

		// replace <br/> by \n
		link = link.replaceAll("<" + zeroOrMoreWhitespaces + "br" + zeroOrMoreWhitespaces + "/" + zeroOrMoreWhitespaces + ">",
				"\n");
		// replace HTML-tabs by \t
		link = link.replaceAll(
				"<" + zeroOrMoreWhitespaces + "span" + oneOrMoreWhitespaces + "style" + zeroOrMoreWhitespaces + "="
						+ zeroOrMoreWhitespaces + "\"white-space:pre\"" + zeroOrMoreWhitespaces + ">&#9;<"
						+ zeroOrMoreWhitespaces + "/" + zeroOrMoreWhitespaces + "span" + zeroOrMoreWhitespaces + ">",
				"\t");

		return link;
	}
}
