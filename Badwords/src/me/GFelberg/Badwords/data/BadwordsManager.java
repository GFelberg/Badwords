package me.GFelberg.Badwords.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.GFelberg.Badwords.Main;

public class BadwordsManager {

	private Main plugin = Main.getPlugin(Main.class);

	// Files & File Configs Here
	public FileConfiguration badwordscfg;
	public File badwordsfile;
	// --------------------------

	public void setup() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		badwordsfile = new File(plugin.getDataFolder(), "badwords.yml");

		if (!badwordsfile.exists()) {
			try {
				badwordsfile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage("The badwords.yml file has been created");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage("Could not create the badwords.yml file");
			}
		}

		badwordscfg = YamlConfiguration.loadConfiguration(badwordsfile);
	}

	public FileConfiguration getWords() {
		return badwordscfg;
	}

	public void saveWords() {
		try {
			badwordscfg.save(badwordsfile);
			Bukkit.getServer().getConsoleSender().sendMessage("The badwords.yml file has been saved");

		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage("Could not save the badwords.yml file");
		}
	}

	public void reloadWords() {
		badwordscfg = YamlConfiguration.loadConfiguration(badwordsfile);
		Bukkit.getServer().getConsoleSender().sendMessage("The badwords.yml file has been reload");
	}
}