package me.tokyojack.spigot.airdrop.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.tokyojack.spigot.airdrop.Core;

public class ChestOpen implements Listener {

	@EventHandler
	public void onChestOpen(PlayerInteractEvent event) {
		
		// Checks if the player right clicked
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		Block block = event.getClickedBlock();

		if (block.getType() != Material.CHEST)
			return;

		if (!Core.getPlugin().getAirdropLocations().contains(block.getLocation()))
			return;

		Core.getPlugin().getChestParticles().stopBlock(block);
	}

}
