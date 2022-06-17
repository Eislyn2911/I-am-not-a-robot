package events;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMemberRemove extends ListenerAdapter {
	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {

		event.getGuild().retrieveAuditLogs().queueAfter(1, TimeUnit.SECONDS, (logs) -> { // Gotta wait a second for
																							// discord to populate the
																							// logs properly
			boolean isBan = false, isKick = false;
			String reason = "No reason given";
			for (AuditLogEntry log : logs) {
				if (log.getTargetIdLong() == event.getUser().getIdLong()) {
					isBan = log.getType() == ActionType.BAN;
					isKick = log.getType() == ActionType.KICK;
					reason = log.getReason();
					if(reason == null) {
						reason = "No reason given";
					}
					break;
				}
			}
			// banned
			if (isBan == true && isKick == false) {
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("»»———-Message From Mini Cafe———-««");

				String user = event.getUser().getAsMention();
				embed.setDescription(user + " is banned from the server <a:clapPepeIntense:979575083494412308>");
				embed.addField("Reason", reason, false);
				embed.setAuthor("Mini Cafe", null,
						"https://lh3.googleusercontent.com/0qXSQPR4y9ai5eKsqRXTyd3XRTf64VDgq-Lm4x6Hgec76dFoOsvaDwDLBAJUCX124padsv4=s85");
				embed.setThumbnail(event.getUser().getAvatarUrl());
				embed.setColor(new Color(0xf7cac9));
				embed.setFooter("Created by Eislyn",
						"https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");

				event.getGuild().getTextChannelsByName("｜�?�｜cafe", true).get(0).sendTyping().queue();
				event.getGuild().getTextChannelsByName("｜�?�｜cafe", true).get(0).sendMessageEmbeds(embed.build()).queue();
				//kicked
			} else if (isKick == true && isBan == false) {
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("»»———-Message From Mini Cafe———-««");

				String user = event.getUser().getAsMention();
				embed.setDescription(user + " is kicked from the server <a:clapPepeOriginal:979575068843720714>");
				embed.addField("Reason", reason, false);
				embed.setAuthor("Mini Cafe", null,
						"https://lh3.googleusercontent.com/0qXSQPR4y9ai5eKsqRXTyd3XRTf64VDgq-Lm4x6Hgec76dFoOsvaDwDLBAJUCX124padsv4=s85");
				embed.setThumbnail(event.getUser().getAvatarUrl());
				embed.setColor(new Color(0xf7cac9));
				embed.setFooter("Created by Eislyn",
						"https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");

				event.getGuild().getTextChannelsByName("｜�?�｜cafe", true).get(0).sendTyping().queue();
				event.getGuild().getTextChannelsByName("｜�?�｜cafe", true).get(0).sendMessageEmbeds(embed.build()).queue();

			} else if (isBan == false && isKick == false) {
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("»»———-Message From Mini Cafe———-««");

				String user = event.getUser().getAsMention();
				embed.setDescription(user + " has left the server <:catsadbear:979566657259339827>");
				embed.setAuthor("Mini Cafe", null,
						"https://lh3.googleusercontent.com/0qXSQPR4y9ai5eKsqRXTyd3XRTf64VDgq-Lm4x6Hgec76dFoOsvaDwDLBAJUCX124padsv4=s85");
				embed.setThumbnail(event.getUser().getAvatarUrl());
				embed.setColor(new Color(0xf7cac9));
				embed.setFooter("Created by Eislyn",
						"https://instagram.fkul8-1.fna.fbcdn.net/v/t51.2885-19/274705642_764618191171133_5230831344965380225_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fkul8-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=I71MXPk1_TQAX8LsRsz&edm=ABfd0MgBAAAA&ccb=7-5&oh=00_AT8iNwPITvsp-YFNXd9yPjHdl43KlVyUEgNYBbgFtdmxsg&oe=62951229&_nc_sid=7bff83");

				event.getGuild().getTextChannelsByName("｜�?�｜cafe", true).get(0).sendTyping().queue();
				event.getGuild().getTextChannelsByName("｜�?�｜cafe", true).get(0).sendMessageEmbeds(embed.build()).queue();
			}
		});

	}

}
