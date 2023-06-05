package me.GFelberg.Badwords.events;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.GFelberg.Badwords.data.BadwordsSystem;

public class BadwordsEvent implements Listener {

	@EventHandler
	public void onBadwords(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		String msg = event.getMessage();
		String censored = msg;

		for (String s : BadwordsSystem.words) {
			if (msg.toLowerCase().contains(s.toLowerCase())) {
				if (p.hasPermission("badwords.bypass")) {
					return;
				} else {
					censored = censorWord(censored, s);
				}
			}
		}
		event.setMessage(censored);
		p.sendMessage(BadwordsSystem.word_message);
	}

	private String censorWord(String message, String word) {
		String regex = "(?i)\\b" + Pattern.quote(word) + "\\b";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(message);
		StringBuilder asterisksBuilder = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			asterisksBuilder.append("*");
		}
		String asterisks = asterisksBuilder.toString();
		return matcher.replaceAll(asterisks);
	}
}
