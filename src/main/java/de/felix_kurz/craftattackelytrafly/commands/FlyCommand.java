package de.felix_kurz.craftattackelytrafly.commands;

import de.felix_kurz.craftattackelytrafly.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public record FlyCommand(Main plugin) implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) return false;
        if (plugin.cfgM.isInSpawnarea(p.getLocation())) return false;
        if (!p.hasPermission("craftattackelytrafly.fly")) return false;
        p.setAllowFlight(!p.getAllowFlight());
        if(p.getAllowFlight()) {
            p.sendMessage("§aFliegen aktiviert");
            p.setVelocity(new Vector(0, 0.2, 0));
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.setFlying(true);
                }
            }.runTaskLater(plugin, 1L);
            return false;
        }
        p.sendMessage("§cFliegen deaktiviert");
        return false;
    }
}

