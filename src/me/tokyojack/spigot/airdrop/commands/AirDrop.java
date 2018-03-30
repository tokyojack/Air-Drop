package me.tokyojack.spigot.airdrop.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tokyojack.spigot.airdrop.utils.kommand.Kommand;

public class AirDrop extends Kommand {

	@Override
	public boolean execute(CommandSender commandSender, String label, String[] args) {
		if (!commandSender.isOp()) {
			commandSender.sendMessage(ChatColor.RED + "You cannot do that command!");
			return false;

		}

		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage(ChatColor.RED + "Only players can do this command!");
			return false;
		}

		((Player) commandSender)
				.openInventory(Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Airdrop creator"));
		return true;
	}
}
