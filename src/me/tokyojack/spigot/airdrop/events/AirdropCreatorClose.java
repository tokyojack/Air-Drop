package me.tokyojack.spigot.airdrop.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import me.tokyojack.spigot.airdrop.Config;
import me.tokyojack.spigot.airdrop.Core;

public class AirdropCreatorClose implements Listener {

	public AirdropCreatorClose(Core core) {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void airdropClose(InventoryCloseEvent event) {
		if (event.getInventory().getName().equals(ChatColor.DARK_GRAY + "Airdrop creator")) {

			Location loc = randomLocation();

			Core.getPlugin().getAirdropLocations().add(loc);

			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
					"&6&lAir-Drop at &a" + loc.getBlockX() + " - " + loc.getBlockY() + " - " + loc.getBlockZ()));
			Bukkit.broadcastMessage(" ");

			loc.getBlock().setType(Material.CHEST);
			Chest chest = (Chest) loc.getBlock().getState();
			chest.getInventory().setContents(event.getInventory().getContents());
			Core.getPlugin().getChestParticles().startBlock(loc.getBlock());
		}
	}

	private Location randomLocation() {
		int worldBorderSize = Config.WORLD_BORDER_SIZE;

		Location loc = new Location(Bukkit.getWorld(Config.WORLD_NAME), 10074 + RandomNumber(worldBorderSize),
				50 + RandomNumber(205), 9445 + RandomNumber(worldBorderSize));

		return loc;

	}

	private int RandomNumber(int s) {
		return new Random().nextInt(s) + 1;
	}

}
