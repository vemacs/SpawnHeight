package com.nullblock.vemacs.spawnheight;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnHeight extends JavaPlugin implements Listener {
	public void onDisable() {
	}

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
		this.getLogger().info(
				"Starting SpawnHeight with max "
						+ this.getConfig().getInt("max") + " and min "
						+ this.getConfig().getInt("min"));
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onMobSpawn(CreatureSpawnEvent event) {
		if (!(event.getLocation().getY() <= this.getConfig().getInt("max") && event
				.getLocation().getY() >= this.getConfig().getInt("min"))
				&& event.getSpawnReason().equals(SpawnReason.NATURAL)) {
			event.setCancelled(true);
		}
	}
}
