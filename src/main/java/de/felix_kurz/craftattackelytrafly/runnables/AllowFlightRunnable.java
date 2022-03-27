package de.felix_kurz.craftattackelytrafly.runnables;

import de.felix_kurz.craftattackelytrafly.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public record AllowFlightRunnable(Main plugin) {

    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(plugin.cfgM.isInSpawnarea(p.getLocation()) && !plugin.boostedPlayers.contains(p.getUniqueId())) {
                        if (!plugin.spawnareaPlayers.contains(p.getUniqueId())) plugin.spawnareaPlayers.add(p.getUniqueId());
                        p.setAllowFlight(true);
                    } else if (plugin.cfgM.isInSpawnarea2(p.getLocation())
                            && p.getGameMode() == GameMode.SURVIVAL
                            && !plugin.boostedPlayers.contains(p.getUniqueId())
                            && plugin.spawnareaPlayers.contains(p.getUniqueId())) {
                        plugin.ptfL.boostPlayer(p);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 5L);
    }

}
