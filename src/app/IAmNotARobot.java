package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import events.GuildMemberJoin;
import events.GuildMemberRemove;
import events.MessageReceived;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class IAmNotARobot {
	public static String prefix = "e!";
	
	public static void main(String[] args) throws LoginException{
		File file = new File("DiscordToken.txt");
		Scanner myReader;
		String token = null;
		try {
			myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
		        token = myReader.nextLine();
		      }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JDABuilder jdaBuilder = JDABuilder.createLight(token).setActivity(Activity.playing("Type e!help for commands"));
		
		jdaBuilder.setAutoReconnect(true);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_PRESENCES);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_VOICE_STATES);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_MESSAGES);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_INVITES);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_BANS);
		jdaBuilder.enableCache(CacheFlag.VOICE_STATE);
		jdaBuilder.enableCache(CacheFlag.ACTIVITY);
		jdaBuilder.enableCache(CacheFlag.ONLINE_STATUS);
		jdaBuilder.enableCache(CacheFlag.CLIENT_STATUS);
		jdaBuilder.enableCache(CacheFlag.ROLE_TAGS);
		jdaBuilder.enableCache(CacheFlag.EMOTE);
		jdaBuilder.enableCache(CacheFlag.MEMBER_OVERRIDES);
		jdaBuilder.setMemberCachePolicy(MemberCachePolicy.ALL);
		
		jdaBuilder.addEventListeners(new MessageReceived());
		jdaBuilder.addEventListeners(new GuildMemberJoin());
		jdaBuilder.addEventListeners(new GuildMemberRemove());
		
		jdaBuilder.build();
	}
}
