package me.GFelberg.Badwords.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.GFelberg.Badwords.utils.BadwordsUtils;

public class BadwordsEvent implements Listener {

	@EventHandler
	public void onBadwords(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();

		for (String s : BadwordsUtils.words) {
			if (event.getMessage().contains(s)) {
				if (p.hasPermission("badwords.bypass")) {
					return;
				} else {
					p.sendMessage(BadwordsUtils.noperm);
					event.setCancelled(true);
				}
			}
		}
	}
}