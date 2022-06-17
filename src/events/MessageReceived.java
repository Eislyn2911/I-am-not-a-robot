package events;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import app.IAmNotARobot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tools.CurrencyTranslator;
import tools.Dictionary;
import tools.Translator;

public class MessageReceived extends ListenerAdapter{
	Translator translator = Translator.getInstance();
	Dictionary dictionary = Dictionary.getInstance();
	
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(event.getAuthor().isBot()==true) {
			return;
		}

		if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "help")) {
			EmbedBuilder embed = new EmbedBuilder();
			
			embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");
			embed.setColor(new Color(0xf7cac9));
			embed.setAuthor("I'm not a robot", "https://www.youtube.com/watch?v=5ngWIDkPP3o&ab_channel=RETKORT", "https://i.pinimg.com/originals/7d/a6/6a/7da66a5aa7656895231f39cc08ddae1d.jpg");
			
			embed.setTitle("»»———-Commands Menu———-««");
			embed.addField("__Prefix__", "``e!``", true);
			embed.addField("__Help__", "``e!help``", true);
			embed.addField("__Invite__", "``e!invite numberOfUser\n" + "e!i numberOfUser``", true);
			embed.addField("__User Info__", "``e!info @user``", true);
			embed.addField("__English Translation__", "``e!translate en\n" + "e!t en``", true);
			embed.addField("__English Definition__", "``e!defintion\n" + "e!d``", true);
			embed.addField("__Currency Exchange__", "``e!exchange baseCurrency targetCurrency amount\n" + "e!e baseCurrency targetCurrency amount``", true);
			embed.addField("__Currency Table__", "``e!helpcurrencytable\n" + "e!helpct``", true);
			embed.addField("__Global Time__", "``e!time timeZoneName\n" + "e!time timeZoneCode``", true);
			embed.addField("__Time Zone List__", "``e!tzl``", true);
			embed.addField("__Set Timer__", "``e!timer minutes``", true);
			embed.addField("__Online Minecraft Players__", "``e!minecraft\n" + "e!mc``", true);
			embed.addField("__Online Genshin Impact Players__", "``e!genshin\n" + "e!gs``", true);
			embed.addField("__About Me__", "``e!about``", true);
			embed.setThumbnail("https://i.pinimg.com/564x/78/c2/16/78c216baf63a1b440438b1ce8dc15a2c.jpg");
				
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessageEmbeds(embed.build()).queue();
		}
		
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "t") || args[0].equalsIgnoreCase(IAmNotARobot.prefix + "translate")) {
			String langTo = args[1];
			String text = "";
			
			if(args.length < 3) {
				return;
			}
			
			else {
				for(int i=2; i<args.length; i++) {
					text += args[i] + " ";
				}
			}
			try {
				translator.setLangTo(langTo);
				translator.setText(text);
				String translatedText = translator.translate();
				String newTranslatedText = translator.replaceHTMLCharacters(translatedText);
				
				event.getChannel().sendMessage("Translated to english\n" + "Result: " + newTranslatedText).queue();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "d") || args[0].equalsIgnoreCase(IAmNotARobot.prefix + "definition")) {
			String wordsForSearch = "";
			
			if(args.length < 2) {
				return;
			}
			else {
				for(int i=1; i<args.length; i++) {
					wordsForSearch += args[i] + " ";
				}
				dictionary.setWord(wordsForSearch);
				String response = dictionary.getResponse();
				
				EmbedBuilder embed = dictionary.extractDataToEmbed(response);
				
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessageEmbeds(embed.build()).queue();
			}
		}
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "about")){
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("»»———-About Me———-««");
			embed.setColor(new Color(0xf7cac9));
			embed.setThumbnail("https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");

			embed.addField("Profile", "Name: Eislyn\n Age: 20\n Major: Software Engineering\n Semester: Year 3 Semester 1", false);
			embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");

			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessageEmbeds(embed.build()).queue();
		}
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "invite") || args[0].equalsIgnoreCase(IAmNotARobot.prefix + "i")){
			
			int expireTime = 43200;
			
			int maxUser = Integer.parseInt(args[1]);
			if(maxUser > 5) {
				event.getChannel().sendMessage("You can only create maximum of 5 invite links at once.").queue();
				return;
			}
			else if(maxUser < 1) {
				event.getChannel().sendMessage("Max user cannot be lower than 1.").queue();
				return;
			}
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("»»———-Invite Link———-««");
			embed.setColor(new Color(0xf7cac9));
			embed.setThumbnail(event.getMember().getAvatarUrl());
			embed.setDescription(event.getMember().getAsMention() + ", here is your invite link. The link will expire in " + expireTime/60/60 + " hours" + " and it can be used on " + maxUser + " user only.");
			embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");
			embed.setImage("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/a7285d56047029.599df4921b34c.jpg");
			
			try {
				String link = event.getTextChannel().createInvite().setMaxAge(expireTime).setMaxUses(maxUser).complete().getUrl().toString();
				embed.addField("__Link__", link, true);
			}catch(NumberFormatException e) {
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Number of user(s) can only be in integer.").queue();
			}
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessageEmbeds(embed.build()).queue();
		}
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "exchange") || args[0].equalsIgnoreCase(IAmNotARobot.prefix + "e")) {
			if(args.length < 3) {
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Enter e!baseCurrency targetCurrency amountToConvert");
				return;
			}
			
			String baseCurrency = args[1];
			String targetCurrency = args[2];
			double amountToConvert = Double.parseDouble(args[3]);
			
			CurrencyTranslator currencyTranslator = new CurrencyTranslator(baseCurrency, targetCurrency, amountToConvert);
			String responseStr = currencyTranslator.getResponse("gbsvqxCtG9vbw9mqbezaFWVLYV9iJaJjt1OwmcFh");
			currencyTranslator.extractData(responseStr);
			
			//Get data
			String updated = currencyTranslator.getUpdated();
			String code = currencyTranslator.getCode();
			double convertedAmount = currencyTranslator.calculateConversion();
			
			//Print result
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("»»———-Currency Exchange———-««");
			embed.setColor(new Color(0xf7cac9));
			embed.setDescription("Converted from " + currencyTranslator.getBaseCurrency() + " to " + currencyTranslator.getTargetCurrency());
			embed.addField("Amount to convert", currencyTranslator.getAmountToConvert() + currencyTranslator.getBaseCurrency(), false);
			embed.addField("Converted amount", convertedAmount + code, false);
			embed.addField("Last updated", updated, false);
			
			embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessageEmbeds(embed.build()).queue();
		}
		
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "helpcurrencytable") || args[0].equalsIgnoreCase(IAmNotARobot.prefix + "helpct")) {
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("»»———-Currency Table———-««");
			embed.setColor(new Color(0xf7cac9));
			embed.setDescription("**__Code Names List__**\r\n"
					+ "AED	United Arab Emirates Dirham\r\n"
					+ "AFN	Afghan Afghani\r\n"
					+ "ALL	Albanian Lek\r\n"
					+ "AMD	Armenian Dram\r\n"
					+ "ANG	NL Antillean Guilder\r\n"
					+ "AOA	Angolan Kwanza\r\n"
					+ "ARS	Argentine Peso\r\n"
					+ "AUD	Australian Dollar\r\n"
					+ "AWG	Aruban Florin\r\n"
					+ "AZN	Azerbaijani Manat\r\n"
					+ "BAM	Bosnia-Herzegovina Convertible Mark\r\n"
					+ "BBD	Barbadian Dollar\r\n"
					+ "BDT	Bangladeshi Taka\r\n"
					+ "BGN	Bulgarian Lev\r\n"
					+ "BHD	Bahraini Dinar\r\n"
					+ "BIF	Burundian Franc\r\n"
					+ "BIH	Bosnia and Herzegovina convertible mark\r\n"
					+ "BMD	Bermudan Dollar\r\n"
					+ "BND	Brunei Dollar\r\n"
					+ "BOB	Bolivian Boliviano\r\n"
					+ "BRL	Brazilian Real\r\n"
					+ "BSD	Bahamian Dollar\r\n"
					+ "BTC	Bitcoin\r\n"
					+ "BTN	Bhutanese Ngultrum\r\n"
					+ "BWP	Botswanan Pula\r\n"
					+ "BYN	Belarusian ruble\r\n"
					+ "BYR	Belarusian Ruble\r\n"
					+ "BZD	Belize Dollar\r\n"
					+ "CAD	Canadian Dollar\r\n"
					+ "CDF	Congolese Franc\r\n"
					+ "CHF	Swiss Franc\r\n"
					+ "CLF	Unidad de Fomento\r\n"
					+ "CLP	Chilean Peso\r\n"
					+ "CNY	Chinese Yuan\r\n"
					+ "COP	Coombian Peso\r\n"
					+ "CRC	Costa Rican Colón\r\n"
					+ "CUC	Cuban Convertible Peso\r\n"
					+ "CUP	Cuban Peso\r\n"
					+ "CVE	Cape Verdean Escudo\r\n"
					+ "CZK	Czech Republic Koruna\r\n"
					+ "DJF	Djiboutian Franc\r\n"
					+ "DKK	Danish Krone\r\n"
					+ "DOP	Dominican Peso\r\n"
					+ "DZD	Algerian Dinar\r\n"
					+ "EGP	Egyptian Pound\r\n"
					+ "ERN	Eritrean Nakfa\r\n"
					+ "ETB	Ethiopian Birr\r\n"
					+ "ETH	Ethereum\r\n"
					+ "EUR	Euro\r\n"
					+ "FJD	Fijian Dollar\r\n"
					+ "FKP	Falkland Islands Pound\r\n"
					+ "GBP	British Pound Sterling\r\n"
					+ "GEL	Georgian Lari\r\n"
					+ "GGP	Guernsey pound\r\n"
					+ "GHS	Ghanaian Cedi\r\n"
					+ "GIP	Gibraltar Pound\r\n"
					+ "GMD	Gambian Dalasi\r\n"
					+ "GNF	Guinean Franc\r\n"
					+ "GTQ	Guatemalan Quetzal\r\n"
					+ "GYD	Guyanaese Dollar\r\n"
					+ "HKD	Hong Kong Dollar\r\n"
					+ "HNL	Honduran Lempira\r\n"
					+ "HRK	Croatian Kuna\r\n"
					+ "HRV	Croatian kuna\r\n"
					+ "HTG	Haitian Gourde\r\n"
					+ "HUF	Hungarian Forint\r\n"
					+ "IDR	Indonesian Rupiah\r\n"
					+ "ILS	Israeli New Sheqel\r\n"
					+ "IMP	Manx pound\r\n"
					+ "INR	Indian Rupee\r\n"
					+ "IQD	Iraqi Dinar\r\n"
					+ "IRR	Iranian Rial\r\n"
					+ "ISK	Icelandic Króna\r\n"
					+ "JEP	Jersey pound\r\n"
					+ "JMD	Jamaican Dollar\r\n"
					+ "JOD	Jordanian Dinar\r\n"
					+ "JPY	Japanese Yen\r\n"
					+ "KES	Kenyan Shilling\r\n"
					+ "KGS	Kyrgystani Som\r\n"
					+ "KHR	Cambodian Riel\r\n"
					+ "KMF	Comorian Franc\r\n"
					+ "KPW	North Korean Won\r\n"
					+ "KRW	South Korean Won\r\n"
					+ "KWD	Kuwaiti Dinar\r\n"
					+ "KYD	Cayman Islands Dollar\r\n"
					+ "KZT	Kazakhstani Tenge\r\n"
					+ "LAK	Laotian Kip\r\n"
					+ "LBP	Lebanese Pound\r\n"
					+ "LKR	Sri Lankan Rupee\r\n"
					+ "LRD	Liberian Dollar\r\n"
					+ "LSL	Lesotho Loti\r\n"
					+ "LTC	Litecoin\r\n"
					+ "LTL	Lithuanian Litas\r\n"
					+ "LVL	Latvian Lats\r\n"
					+ "LYD	Libyan Dinar\r\n"
					+ "MAD	Moroccan Dirham\r\n"
					+ "MDL	Moldovan Leu\r\n"
					+ "MGA	Malagasy Ariary\r\n"
					+ "MKD	Macedonian Denar\r\n"
					+ "MMK	Myanma Kyat\r\n"
					+ "MNT	Mongolian Tugrik\r\n"
					+ "MOP	Macanese Pataca\r\n"
					+ "MRO	Mauritanian ouguiya\r\n"
					+ "MUR	Mauritian Rupee\r\n"
					+ "MVR	Maldivian Rufiyaa\r\n"
					+ "MWK	Malawian Kwacha\r\n"
					+ "MXN	Mexican Peso\r\n"
					+ "MYR	Malaysian Ringgit\r\n"
					+ "MZN	Mozambican Metical\r\n"
					+ "NAD	Namibian Dollar\r\n"
					+ "NGN	Nigerian Naira\r\n"
					+ "NIO	Nicaraguan Córdoba\r\n"
					+ "NOK	Norwegian Krone\r\n"
					+ "NPR	Nepalese Rupee\r\n"
					+ "NZD	New Zealand Dollar\r\n"
					+ "OMR	Omani Rial\r\n"
					+ "PAB	Panamanian Balboa\r\n"
					+ "PEN	Peruvian Nuevo Sol\r\n"
					+ "PGK	Papua New Guinean Kina\r\n"
					+ "PHP	Philippine Peso\r\n"
					+ "PKR	Pakistani Rupee\r\n"
					+ "PLN	Polish Zloty\r\n"
					+ "PYG	Paraguayan Guarani\r\n"
					+ "QAR	Qatari Rial\r\n"
					+ "RON	Romanian Leu\r\n"
					+ "RSD	Serbian Dinar\r\n"
					+ "RUB	Russian Ruble\r\n"
					+ "RWF	Rwandan Franc\r\n"
					+ "SAR	Saudi Riyal\r\n"
					+ "SBD	Solomon Islands Dollar\r\n"
					+ "SCR	Seychellois Rupee\r\n"
					+ "SDG	Sudanese Pound\r\n"
					+ "SEK	Swedish Krona\r\n"
					+ "SGD	Singapore Dollar\r\n"
					+ "SHP	Saint Helena Pound\r\n"
					+ "SLL	Sierra Leonean Leone\r\n"
					+ "SOS	Somali Shilling\r\n"
					+ "SRD	Surinamese Dollar\r\n"
					+ "SSP	South Sudanese pounds\r\n"
					+ "STD	São Tomé and Príncipe dobra\r\n"
					+ "SVC	Salvadoran Colón\r\n"
					+ "SYP	Syrian Pound\r\n"
					+ "SZL	Swazi Lilangeni\r\n"
					+ "THB	Thai Baht\r\n"
					+ "TJS	Tajikistani Somoni\r\n"
					+ "TMT	Turkmenistani Manat\r\n"
					+ "TND	Tunisian Dinar\r\n"
					+ "TOP	Tongan Paʻanga\r\n"
					+ "TRY	Turkish Lira\r\n"
					+ "TTD	Trinidad and Tobago Dollar\r\n"
					+ "TWD	New Taiwan Dollar\r\n"
					+ "TZS	Tanzanian Shilling\r\n"
					+ "UAH	Ukrainian Hryvnia\r\n"
					+ "UGX	Ugandan Shilling\r\n"
					+ "URY	Uruguayan peso\r\n"
					+ "USD	US Dollar\r\n"
					+ "UYU	Uruguayan Peso\r\n"
					+ "UZS	Uzbekistan Som\r\n"
					+ "VEF	Venezuelan Bolívar\r\n"
					+ "VND	Vietnamese Dong\r\n"
					+ "VUV	Vanuatu Vatu\r\n"
					+ "WST	Samoan Tala\r\n"
					+ "XAF	CFA Franc BEAC\r\n"
					+ "XAG	Silver Ounce\r\n"
					+ "XAU	Gold Ounce\r\n"
					+ "XCD	East Caribbean Dollar\r\n"
					+ "XDR	Special drawing rights\r\n"
					+ "XOF	CFA Franc BCEAO\r\n"
					+ "XPF	CFP Franc\r\n"
					+ "XRP	Ripple\r\n"
					+ "YER	Yemeni Rial\r\n"
					+ "ZAR	South African Rand\r\n"
					+ "ZMK	Zambian Kwacha\r\n"
					+ "ZMK	Zambian Kwacha\r\n"
					+ "ZWL	Zimbabwean dollar");
			
			embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessageEmbeds(embed.build()).queue();
		}
		
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "info")) {
			if(args.length < 2) {
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Enter tag to show user info");
				return;
			}
			else if(args.length > 2) {
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Tag only one user");
				return;
			}
			else {
				Member member = event.getGuild().getMemberById(args[1].replace("<@", "").replace(">", ""));
				String memberTag = member.getAsMention();
				String name = member.getEffectiveName();
				String nickname = member.getNickname();
				String memberId = member.getId().replace("@<", "").replace(">", "");
				
				String avatarUrl = member.getEffectiveAvatarUrl();
				
				String onlineStatus = member.getOnlineStatus().toString().toLowerCase().substring(0, 1).toUpperCase() + member.getOnlineStatus().toString().toLowerCase().substring(1).replace("_", " ");
				String joinDate = member.getTimeJoined().toString();
				String createTime = event.getMessage().getMentions().getUsers().get(0).getTimeCreated().toString();
				
				List<String> roles = new ArrayList<String>();
				String rolesString = "";
				
				List<Activity> activities = null;
				try {
					activities = member.getActivities();
				} catch(IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
				
				for(int i=0; i<member.getRoles().size(); i++) {
					String role = member.getRoles().get(i).getAsMention();
					roles.add(role);
					rolesString += role + "\n";
				}
				String activitesString = "";
				for(int i=0; i<activities.size(); i++) {
					activitesString += (activities.get(i).getType().toString().toLowerCase().substring(0, 1).toUpperCase() + activities.get(i).getType().toString().replace("_", " ").toLowerCase().substring(1)) + ": " + activities.get(i).getName() + "\n";
				}
				
//				String[] permissions = member.getPermissionsExplicit().toString().replace("[", "").replace("]", "").replace("_", " ").split(",");
//				String permissionString = "";
//				for(int i=0; i<permissions.length; i++) {
//					permissionString += (permissions[i].toLowerCase().substring(0, 1).toUpperCase() +  permissions[i].substring(1).toLowerCase()) + "\n";
//				}
				
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("»»———-User Info———-««");
				embed.setThumbnail(avatarUrl);
				embed.setColor(new Color(0xf7cac9));
				embed.setDescription("User info of " + memberTag);
				embed.addField("▼__Info__", "Name: " + name + "\n" + "Nickname: " + nickname + "\n" + "Id: " + memberId + "\n" + "Online Status: " + onlineStatus + "\n" + "Join Date: " + joinDate + "\n" + "Create Date: " + createTime + "\n", false);
				
				embed.addField("▼__Activities__", activitesString, false);
				embed.addField("▼__Roles__", rolesString, true);
				//embed.addField("▼__Permissions__", permissionString, true);
				
				embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessageEmbeds(embed.build()).queue();
			}
		}
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "minecraft") || args[0].equalsIgnoreCase(IAmNotARobot.prefix + "mc")){
			List<Member> members = event.getGuild().getMembers();
			List<Member> onlineMinecraftMembers = new ArrayList<Member>();
			
			for(int i=0; i<members.size(); i++) {
				for(int j=0; j<members.get(i).getActivities().size(); j++) {
					if(members.get(i).getActivities().get(j).getName().toString().equals("Minecraft")) {
						Member member = members.get(i);
						onlineMinecraftMembers.add(member);
					}
				}
			}
			
			String onlineMinecraftMembersString = "";
			
			for(int i=0; i<onlineMinecraftMembers.size(); i++) {
				onlineMinecraftMembersString += onlineMinecraftMembers.get(i).getAsMention();
			}
			
			if(onlineMinecraftMembersString == "") {
				onlineMinecraftMembersString = "No one is playing Minecraft currently";
			}
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("»»———-Online Minecraft Players———-««");
			embed.setImage("https://64.media.tumblr.com/07db87dec1198e0d549019bcea1d59bf/7d120d932ba544f7-d3/s400x600/1d1cc9575c7af8e875d841df343955819377e3e6.gifv");
			embed.setColor(new Color(0xf7cac9));
			
			embed.addField("Number of players playing minecraft: " + onlineMinecraftMembers.size(), onlineMinecraftMembersString, false);
			
			embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessageEmbeds(embed.build()).queue();
		}
		
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "genshin") || args[0].equalsIgnoreCase(IAmNotARobot.prefix + "gs")){
			List<Member> members = event.getGuild().getMembers();
			List<Member> onlineGenshinMembers = new ArrayList<Member>();
			
			for(int i=0; i<members.size(); i++) {
				for(int j=0; j<members.get(i).getActivities().size(); j++) {
					if(members.get(i).getActivities().get(j).getName().toString().equals("Genshin Impact")) {
						Member member = members.get(i);
						onlineGenshinMembers.add(member);
					}
				}
			}
			
			String onlineMinecraftMembersString = "";
			
			for(int i=0; i<onlineGenshinMembers.size(); i++) {
				onlineMinecraftMembersString += onlineGenshinMembers.get(i).getAsMention();
			}
			
			if(onlineMinecraftMembersString == "") {
				onlineMinecraftMembersString = "No one is playing Genshin Impact currently";
			}
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("»»———-Online Genshin Impact Players———-««");
			embed.setImage("https://gamezo.co.uk/wp-content/uploads/2022/02/Genshin-Characters-Wallpaper.jpg");
			embed.setColor(new Color(0xf7cac9));
			
			embed.addField("Number of players playing Genshin Impact: " + onlineGenshinMembers.size(), onlineMinecraftMembersString, false);
			
			embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessageEmbeds(embed.build()).queue();
		}
		
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "timer")) {
			if(args.length < 2) {
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Command incomplete. Please provide time in minutes. ").queue();
			}
			
			int minutes = Integer.parseInt(args[1]);
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(args[1] + " minute(s) timer is set.").queue();
			
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(args[1] + " minute(s) timer is completed. " + event.getAuthor().getAsMention()).queue();;
				}
			};
			timer.schedule(task, minutes*60000);
		}
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "time")) {
			if(args.length < 2) {
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Command incomplete. Please provide timezone.").queue();
			}
			
			String timeZone = args[1];
			TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
			SimpleDateFormat dateFormat = new SimpleDateFormat();
			Date date = new Date();
			
			String currentDate = dateFormat.format(date);
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(currentDate).queue();
		}
		else if(args[0].equalsIgnoreCase(IAmNotARobot.prefix + "timezonelist") || args[0].equalsIgnoreCase(IAmNotARobot.prefix + "tzl")) {
			String timeZoneList[] = TimeZone.getAvailableIDs();
			String timeZoneListString = "";
			
			for(int i=0; i<timeZoneList.length; i++) {
				timeZoneListString += timeZoneList[i] + "\n";
			}
			
			File file = new File("Timezone List.txt");
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(timeZoneListString);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Time Zone list").addFile(file, "txt").queue();
			
		}
	}
}
