package me.GFelberg.Badwords.data;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import me.GFelberg.Badwords.Main;

public class BadwordsSystem {

	public static List<String> words = new ArrayList<String>();
	public static String word_added, word_removed, word_already_added, word_notlisted;
	public static String words_empty, word_message;

	public static void loadVariables() {
		word_added = Main.getInstance().getConfig().getString("Word.Added").replace("&", "§");
		word_removed = Main.getInstance().getConfig().getString("Word.Removed").replace("&", "§");
		word_already_added = Main.getInstance().getConfig().getString("Word.AlreadyAdded").replace("&", "§");
		word_notlisted = Main.getInstance().getConfig().getString("Word.NotListed").replace("&", "§");
		words_empty = Main.getInstance().getConfig().getString("List.NoWords").replace("&", "§");
		word_message = Main.getInstance().getConfig().getString("Badwords.Message").replace("&", "§");
	}

	public void addWord(Player p, String word) {

		if (words.contains(word.toLowerCase())) {
			p.sendMessage(word_already_added.replace("%badwords_word_already%", word.toLowerCase()));
			return;
		} else {
			FileConfiguration customConfig = BadwordsConfig.getConfig();
			customConfig.set("BannedWords." + word.toLowerCase() + ".UUID", p.getUniqueId().toString());
			customConfig.set("BannedWords." + word.toLowerCase() + ".Added By", p.getName());
			BadwordsConfig.saveConfig();
			words.add(word.toLowerCase());
			p.sendMessage(word_added.replace("%badwords_word_added%", word.toLowerCase()));
		}
	}

	public void removeWord(Player p, String word) {

		if (!(words.contains(word.toLowerCase()))) {
			p.sendMessage(word_notlisted.replace("%badwords_word_notlisted%", word.toLowerCase()));
			return;
		} else {
			FileConfiguration customConfig = BadwordsConfig.getConfig();
			customConfig.set("BannedWords." + word.toLowerCase(), null);
			BadwordsConfig.saveConfig();
			words.remove(word);
			p.sendMessage(word_removed.replace("%badwords_word_removed%", word.toLowerCase()));
		}
	}

	public void listWords(Player p) {
		FileConfiguration customConfig = BadwordsConfig.getConfig();

		if (customConfig.getString("BannedWords") == null) {
			p.sendMessage(words_empty);
			return;
		} else {
			p.sendMessage("\n===============");
			p.sendMessage(ChatColor.AQUA + "Banned Words:\n");
			Collections.sort(words);
			for (String s : words) {
				p.sendMessage(ChatColor.YELLOW + " - " + s.toLowerCase());
			}
			p.sendMessage("===============\n");
		}
	}

	public static void loadWords() {
		String path = "plugins/Badwords/banned_words.yml";

		try (FileReader reader = new FileReader(path)) {
			Yaml yaml = new Yaml();
			Map<String, Map<String, Map<String, String>>> data = yaml.load(reader);
			FileConfiguration customConfig = BadwordsConfig.getConfig();

			if (customConfig.getString("BannedWords") == null) {
				return;
			} else {
				if (data.containsKey("BannedWords")) {
					Map<String, Map<String, String>> bannedWords = data.get("BannedWords");

					for (Map.Entry<String, Map<String, String>> entry : bannedWords.entrySet()) {
						String word = entry.getKey();
						words.add(word);
						Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Banned word loaded: " + word);
					}
				}
			}
		} catch (IOException e) {
			System.out.println(ChatColor.RED + "Could not read the banned_words.yml file: " + e.getMessage());
		}
	}
}