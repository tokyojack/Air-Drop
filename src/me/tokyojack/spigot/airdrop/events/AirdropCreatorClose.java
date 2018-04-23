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
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if (!event.getInventory().getName().equals(ChatColor.DARK_GRAY + "Airdrop creator")) 
			return;

		Location randomLocationInWorld = randomLocationInWorld();
		
		Core.getPlugin().getAirdropLocations().add(randomLocationInWorld);

		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
				"&6&lAir-Drop at &a" + 
				randomLocationInWorld.getBlockX() + " - " + 
				randomLocationInWorld.getBlockY() + " - " + 
				randomLocationInWorld.getBlockZ()));
		Bukkit.broadcastMessage(" ");

		randomLocationInWorld.getBlock().setType(Material.CHEST);
		Chest chest = (Chest) randomLocationInWorld.getBlock().getState();
		chest.getInventory().setContents(event.getInventory().getContents());
		
		Core.getPlugin().getChestParticles().startBlock(randomLocationInWorld.getBlock());
	}

	private Location randomLocationInWorld() {
		int worldBorderSize = Config.WORLD_BORDER_SIZE;

		Location loc = new Location(Bukkit.getWorld(Config.WORLD_NAME), 10074 + RandomNumber(worldBorderSize),
				50 + RandomNumber(205), 9445 + RandomNumber(worldBorderSize));

		return loc;
	}

	private int RandomNumber(int randomNumber) {
		return new Random().nextInt(randomNumber) + 1;
	}

}
