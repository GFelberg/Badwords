package me.GFelberg.Badwords.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.GFelberg.Badwords.Main;

public class BadwordsUtils {

	public static String prefix;

	public static void loadVariables() {
		prefix = Main.getInstance().getConfig().getString("Badwords.Prefix").replace("&", "§");
	}

	public void reloadConfig(Player p) {

		if (!(p.hasPermission("badwords.reload"))) {
			p.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
		} else {
			Main.getInstance().reloadConfig();
			Main.getInstance().loadVariables();
			p.sendMessage(prefix + " " + ChatColor.GREEN + "Plugin reloaded successfully!");
			Bukkit.getConsoleSender().sendMessage("==========================================");
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Badwords Plugin reloaded");
			Bukkit.getConsoleSender().sendMessage("==========================================");
		}
	}

	public void helpPage(Player p) {
		HelpPageUtils helpUtils = new HelpPageUtils();
		p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
		p.sendMessage(ChatColor.AQUA + "Badwords - Help Commands");
		p.sendMessage(ChatColor.YELLOW + "/bd help : " + helpUtils.getHelp_page());
		p.sendMessage(ChatColor.YELLOW + "/bd add <word> : " + helpUtils.getHelp_addWord());
		p.sendMessage(ChatColor.YELLOW + "/bd remove <word> : " + helpUtils.getHelp_removeWord());
		p.sendMessage(ChatColor.YELLOW + "/bd list : " + helpUtils.getHelp_listWords());
		p.sendMessage(ChatColor.YELLOW + "/bd reload : " + helpUtils.getHelp_reload());
		p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
	}
}