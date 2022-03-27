package de.felix_kurz.craftattackelytrafly.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) return false;
        if(!p.hasPermission("craftattackelytrafly.fly")) return false;
        if(args.length == 0) {
            p.setFlySpeed(0.2f);
            return false;
        }
        try {
            float speed = Float.parseFloat(args[0]);
            if(speed < 0 || speed > 10) {
                p.sendMessage("§cSpeed muss zwischen 0 und 10 sein");
                return false;
            }
            p.setFlySpeed(speed / 10);
        } catch (Exception e) {
            p.sendMessage("§cBitte verwende §6/flyspeed <§espeed§6>");
        }
        return false;
    }
}
