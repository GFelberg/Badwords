package me.GFelberg.Badwords.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.GFelberg.Badwords.data.BadwordsSystem;
import me.GFelberg.Badwords.utils.BadwordsUtils;

public class Badwords implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("badwords")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command can be only made by players!");
				return true;
			}

			if (!(sender.hasPermission("badwords.admin"))) {
				sender.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
				return true;
			}

			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Usage: /bd help");
				return true;
			}

			Player p = (Player) sender;
			BadwordsUtils utils = new BadwordsUtils();
			BadwordsSystem sys = new BadwordsSystem();

			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("reload")) {
					utils.reloadConfig(p);
				} else if (args[0].equalsIgnoreCase("help")) {
					utils.helpPage(p);
				}
				return true;
			}

			if (args.length == 2) {

				if (args[0].equalsIgnoreCase("add")) {
					sys.addWord(p, args[1]);
				} else if (args[0].equalsIgnoreCase("remove")) {
					sys.removeWord(p, args[1]);
				}
				return true;
			}
		}
		return true;
	}
}