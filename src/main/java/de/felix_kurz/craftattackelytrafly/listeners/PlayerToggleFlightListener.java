package de.felix_kurz.craftattackelytrafly.listeners;

import de.felix_kurz.craftattackelytrafly.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public record PlayerToggleFlightListener(Main plugin) implements Listener {

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        Player p = event.getPlayer();
        if(!plugin.cfgM.isInSpawnarea(p.getLocation())) return;
        if(p.isFlying()) return;
        event.setCancelled(true);
        boostPlayer(p);
    }

    public void boostPlayer(Player p) {
        p.setVelocity(p.getLocation().getDirection().multiply(1.2).add(new Vector(0,1.4,0)));
        p.setGliding(true);
        plugin.boostedPlayers.add(p.getUniqueId());
        p.setAllowFlight(false);
        plugin.spawnareaPlayers.remove(p.getUniqueId());
    }

}
