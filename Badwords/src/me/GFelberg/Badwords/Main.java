package me.GFelberg.Badwords;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.Badwords.commands.Badwords;
import me.GFelberg.Badwords.data.BadwordsManager;
import me.GFelberg.Badwords.events.BadwordsEvent;
import me.GFelberg.Badwords.utils.BadwordsUtils;

public class Main extends JavaPlugin {

	public static BadwordsManager badwords;
	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		loadBadwordsManager();
		BadwordsUtils.loadVariables();
		getCommand("badwords").setExecutor(new Badwords());
		Bukkit.getPluginManager().registerEvents(new BadwordsEvent(), this);
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
		Bukkit.getConsoleSender().sendMessage("Badwords Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin develloped by GFelberg");
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
	}

	public static Main getInstance() {
		return instance;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
		Bukkit.getConsoleSender().sendMessage("Badwords Plugin Disabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin develloped by GFelberg");
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
	}

	public void loadBadwordsManager() {
		badwords = new BadwordsManager();
		badwords.setup();
		badwords.saveWords();
		badwords.reloadWords();
	}
}