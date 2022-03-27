package de.felix_kurz.craftattackelytrafly.listeners;

import de.felix_kurz.craftattackelytrafly.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

public record PlayerToggleGlideEvent(Main plugin) implements Listener {

    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player p)) return;
        if (!event.getEntity().isOnGround()) {
            if (p.getInventory().getItem(EquipmentSlot.CHEST) == null) {
                event.setCancelled(true);
                return;
            }
            if (p.getInventory().getItem(EquipmentSlot.CHEST).getType() == Material.ELYTRA) return;
            event.setCancelled(true);
            return;
        }
        if (plugin.boostedPlayers.contains(p.getUniqueId())) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.setAllowFlight(false);
                    plugin.boostedPlayers.remove(p.getUniqueId());
                }
            }.runTaskLater(plugin, 1L);
        }
    }

}
