package events;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMemberJoin extends ListenerAdapter{
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("»»———-Message From Mini Cafe———-««");
		embed.setDescription("Welcome to Mini Cafe " + event.getMember().getAsMention() + "!!!" + " <:CatLove:873561867514183730>");
		embed.setAuthor("Mini Cafe", null, "https://lh3.googleusercontent.com/0qXSQPR4y9ai5eKsqRXTyd3XRTf64VDgq-Lm4x6Hgec76dFoOsvaDwDLBAJUCX124padsv4=s85");
		embed.setThumbnail(event.getMember().getEffectiveAvatarUrl());
		embed.setColor(new Color(0xf7cac9));
		embed.addField("To-do list", "1. Please read the rules in " + event.getGuild().getTextChannelsByName("｜ꕤ｜rules", true).get(0).getAsMention() + "\r\n" 
		+ "2. Take your roles in " + event.getGuild().getTextChannelsByName("｜ꕤ｜reaction-roles", true).get(0).getAsMention() + "\r\n" 
		+ "3. Take your color roles in " + event.getGuild().getTextChannelsByName("｜ꕤ｜color-roles", true).get(0).getAsMention() + "\r\n" 
		+ "4. Done!!! We hope you have a great time here!!!\r\n\n", false);
		
		embed.setFooter("Created by Eislyn", "https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");
		event.getGuild().getTextChannelsByName("｜�?�｜cafe", true).get(0).sendTyping().queue();
		event.getGuild().getTextChannelsByName("｜�?�｜cafe", true).get(0).sendMessageEmbeds(embed.build()).queue();
		
		String[] rolesStr = {"─────※ ·『••✎Color••�?· ※─────", 
				"───※ ·『••✎Positions••�?· ※────",
				"───※ ·『••✎About Me••�?· ※────",
				"───※ ·『••✎Interests••�?· ※────",
				"─────※ ·『••✎Level••�?· ※─────"};
		
		
		for(int i=0; i<rolesStr.length; i++) {
			event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRolesByName(rolesStr[i], true).get(0)).complete();
		}
	}
	
	

}
