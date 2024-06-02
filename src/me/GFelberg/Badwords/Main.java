package me.GFelberg.Badwords;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.Badwords.commands.Badwords;
import me.GFelberg.Badwords.commands.Badwordslist;
import me.GFelberg.Badwords.data.BadwordsConfig;
import me.GFelberg.Badwords.data.BadwordsSystem;
import me.GFelberg.Badwords.events.BadwordsEvent;
import me.GFelberg.Badwords.utils.BadwordsUtils;

public class Main extends JavaPlugin {

	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		loadBadwordsConfig();
		loadVariables();
		loadCommands();
		BadwordsSystem.loadWords();
		Bukkit.getPluginManager().registerEvents(new BadwordsEvent(), this);
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Badwords Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
	}

	public static Main getInstance() {
		return instance;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Badwords Plugin Disabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
	}

	public void loadBadwordsConfig() {
		BadwordsConfig.setupConfig();
		BadwordsConfig.getConfig().options().copyDefaults(true);
		BadwordsConfig.saveConfig();
	}

	public void loadVariables() {
		BadwordsUtils.loadVariables();
		BadwordsSystem.loadVariables();
	}

	public void loadCommands() {
		getCommand("badwords").setExecutor(new Badwords());
		getCommand("badwordslist").setExecutor(new Badwordslist());
	}
}