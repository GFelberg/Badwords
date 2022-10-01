package me.GFelberg.Badwords.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.GFelberg.Badwords.utils.BadwordsUtils;

public class Badwords implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("badwords")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command can be only made by players!");
				return true;
			}

			if (!(sender.hasPermission("badwords.badwords"))) {
				sender.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
				return true;
			}

			Player p = (Player) sender;
			BadwordsUtils utils = new BadwordsUtils();

			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Usage: /bd help");
				return true;
			}

			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("help")) {
					utils.helpPage(p);
				} else if (args[0].equalsIgnoreCase("list")) {
					utils.listWords(p);
				} else if (args[0].equalsIgnoreCase("reload")) {
					utils.reloadConfig(p);
				}
				return true;
			}

			if (args.length == 2) {
				String word = args[1];

				if (args[0].equalsIgnoreCase("add")) {
					utils.addWord(p, word);
				} else if (args[0].equalsIgnoreCase("remove")) {
					utils.removeWord(p, word);
				}
				return true;
			}
		}
		return true;
	}
}