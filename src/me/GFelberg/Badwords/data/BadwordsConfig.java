package me.GFelberg.Badwords.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class BadwordsConfig {

	private static File file;
	private static FileConfiguration customFile;

	public static void setupConfig() {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("Badwords").getDataFolder(),
				"banned_words.yml");

		if (!(file.exists())) {
			try {
				file.createNewFile();
				Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "The banned_words.yml file has been created");
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not create the banned_words.yml file");
			}
		}

		customFile = YamlConfiguration.loadConfiguration(file);
	}

	public static FileConfiguration getConfig() {
		return customFile;
	}

	public static void saveConfig() {
		try {
			customFile.save(file);
			Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "The banned_words.yml file has been saved");
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not save the banned_words.yml file");
		}
	}

	public static void reloadConfig() {
		customFile = YamlConfiguration.loadConfiguration(file);
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "The banned_words.yml file has been reload");
	}
}