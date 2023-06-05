package me.GFelberg.Badwords.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.GFelberg.Badwords.data.BadwordsSystem;

public class Badwordslist implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("badwordslist")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command can be only made by players!");
				return true;
			}

			if (!(sender.hasPermission("badwords.admin"))) {
				sender.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
				return true;
			}

			if (args.length == 0) {
				Player p = (Player) sender;
				BadwordsSystem sys = new BadwordsSystem();
				sys.listWords(p);
				return true;
			}
		}
		return true;
	}
}