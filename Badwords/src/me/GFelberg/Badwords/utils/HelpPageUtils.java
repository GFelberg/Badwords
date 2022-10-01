package me.GFelberg.Badwords.utils;

import me.GFelberg.Badwords.Main;

public class HelpPageUtils {

	public String getHelp_page() {
		return Main.getInstance().getConfig().getString("Help.Page").replace("&", "§");
	}

	public String getHelp_addWord() {
		return Main.getInstance().getConfig().getString("Help.AddWord").replace("&", "§");
	}

	public String getHelp_removeWord() {
		return Main.getInstance().getConfig().getString("Help.RemoveWord").replace("&", "§");
	}

	public String getHelp_listWords() {
		return Main.getInstance().getConfig().getString("Help.ListWords").replace("&", "§");
	}

	public String getHelp_reload() {
		return Main.getInstance().getConfig().getString("Help.Reload").replace("&", "§");
	}
}