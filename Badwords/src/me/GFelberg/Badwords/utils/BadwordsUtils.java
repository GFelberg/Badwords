package me.GFelberg.Badwords.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.GFelberg.Badwords.Main;

public class BadwordsUtils {

	public static List<String> words;
	public static String prefix, add, remove, already, notlist, nowords, noperm;

	public static void loadVariables() {
		prefix = Main.getInstance().getConfig().getString("Badwords.Prefix").replace("&", "§");
		add = Main.getInstance().getConfig().getString("Badwords.AddWord").replace("&", "§");
		remove = Main.getInstance().getConfig().getString("Badwords.RemoveWord").replace("&", "§");
		already = Main.getInstance().getConfig().getString("Badwords.Already").replace("&", "§");
		notlist = Main.getInstance().getConfig().getString("Badwords.NotList").replace("&", "§");
		nowords = Main.getInstance().getConfig().getString("Badwords.NoWords").replace("&", "§");
		noperm = Main.getInstance().getConfig().getString("Badwords.NoPerm").replace("&", "§");
		words = Main.badwords.badwordscfg.getStringList("BannedWords");
	}

	public void reloadConfig(Player p) {

		if (!(p.hasPermission("badwords.reload"))) {
			p.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
		} else {
			Main.getInstance().reloadConfig();
			loadVariables();
			Main.badwords.saveWords();
			Main.badwords.reloadWords();
			p.sendMessage(prefix + " " + ChatColor.GREEN + "Plugin reloaded successfully!");
			Bukkit.getServer().getConsoleSender().sendMessage("==========================================");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Badwords Plugin reloaded");
			Bukkit.getServer().getConsoleSender().sendMessage("==========================================");
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

	public void addWord(Player p, String word) {

		if (Main.badwords.badwordscfg.getStringList("BannedWords").contains(word)) {
			p.sendMessage(already);
		} else {
			words.add(word.toString());
			Main.badwords.badwordscfg.set("BannedWords", words);
			Main.badwords.saveWords();
			Main.badwords.reloadWords();
			p.sendMessage(add);
		}
	}

	public void removeWord(Player p, String word) {

		if (!(Main.badwords.badwordscfg.getStringList("BannedWords").contains(word))) {
			p.sendMessage(notlist);
		} else {
			words.remove(word);
			Main.badwords.badwordscfg.set("BannedWords", words);
			Main.badwords.saveWords();
			Main.badwords.reloadWords();
			p.sendMessage(remove);
		}
	}

	public void listWords(Player p) {

		if (Main.badwords.badwordscfg.getStringList("BannedWords").isEmpty()) {
			p.sendMessage(nowords);
			return;
		} else {
			p.sendMessage("");
			p.sendMessage("===============");
			p.sendMessage(ChatColor.AQUA + "Banned Words:");
			p.sendMessage("");
			for (String s : words) {
				p.sendMessage(ChatColor.YELLOW + " - " + s);
			}
			p.sendMessage("===============");
			p.sendMessage("");
		}
	}
}