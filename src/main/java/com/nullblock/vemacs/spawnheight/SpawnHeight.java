package com.nullblock.vemacs.spawnheight;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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
						+ this.getConfig().getInt("max") + ", min "
						+ this.getConfig().getInt("min") + " and probability "
						+ Float.parseFloat(this.getConfig().getString("prob")));
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMobSpawn(CreatureSpawnEvent event) {
		if (((int) event.getLocation().getY() > this.getConfig().getInt("max") || (int) event
				.getLocation().getY() < this.getConfig().getInt("min"))
				&& event.getSpawnReason().equals(SpawnReason.NATURAL)) {
			float f = Float.parseFloat(this.getConfig().getString("prob"));
			if (!canSpawn(f)) {
                Location loc = event.getLocation();
                double x = loc.getX();
                double y = -20D;
                double z = loc.getZ();
                String worldname = loc.getWorld().getName();
				event.getEntity().teleport(new Location(Bukkit.getWorld(worldname), x, y, z));
			}
		}
	}

	public boolean canSpawn(float f) {
		float compare = (float) Math.random();
		if (compare <= f) {
			return true;
		}
		return false;
	}

}
