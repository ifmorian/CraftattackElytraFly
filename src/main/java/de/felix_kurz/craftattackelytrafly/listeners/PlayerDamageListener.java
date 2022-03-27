package de.felix_kurz.craftattackelytrafly.listeners;

import de.felix_kurz.craftattackelytrafly.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public record PlayerDamageListener(Main plugin) implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player p)) return;
        if ((event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL
        || event.getCause() == EntityDamageEvent.DamageCause.FALL) && plugin.boostedPlayers.contains(p.getUniqueId())) event.setCancelled(true);
    }

}
