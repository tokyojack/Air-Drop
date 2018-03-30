package me.tokyojack.spigot.airdrop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.tokyojack.spigot.airdrop.commands.AirDrop;
import me.tokyojack.spigot.airdrop.events.AirdropCreatorClose;
import me.tokyojack.spigot.airdrop.utils.BlockRunnable;
import me.tokyojack.spigot.airdrop.utils.ParticleEffect;
import me.tokyojack.spigot.airdrop.utils.kommand.KommandManager;

public class Core extends JavaPlugin {

	private static Core plugin;

	public static Core getPlugin() {
		return plugin;
	}

	@Getter
	private List<Location> airdropLocations = new ArrayList<Location>();

	@Getter
	BlockRunnable chestParticles = new BlockRunnable(20, this) {

		@Override
		protected void start(Block block) {

		}

		@Override
		protected void tick(Block block) {
			ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 1, 50, block.getLocation(), 5);
		}

		@Override
		protected void stop(Block block) {
		}

	};

	public void onEnable() {
		plugin = this;

		new KommandManager().addCommand(new AirDrop()).build();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new AirdropCreatorClose(this), this);
	}

}