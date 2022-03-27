package de.felix_kurz.craftattackelytrafly.configuration;

import de.felix_kurz.craftattackelytrafly.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private Main plugin;
    private FileConfiguration cfg;

    public Location spawnPoint;
    public double radius;

    public ConfigManager(Main plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        this.cfg = plugin.getConfig();
        setupSpawnareas();
    }

    public void setupSpawnareas() {
        spawnPoint = new Location(Bukkit.getWorld(cfg.getString("spawnpoint.world")), cfg.getDouble("spawnpoint.x"), cfg.getDouble("spawnpoint.y"), cfg.getDouble("spawnpoint.z"));
        radius = cfg.getDouble("radius");
    }

    public boolean isInSpawnarea(Location l) {
        if (!spawnPoint.getWorld().equals(l.getWorld())) return false;
        return spawnPoint.distance(l) <= radius;
    }

    public boolean isInSpawnarea2(Location l) {
        if (!spawnPoint.getWorld().equals(l.getWorld())) return false;
        return spawnPoint.distance(l) <= radius + 10;
    }
}
