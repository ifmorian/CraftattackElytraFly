package de.felix_kurz.craftattackelytrafly.main;
import de.felix_kurz.craftattackelytrafly.commands.FlyCommand;
import de.felix_kurz.craftattackelytrafly.commands.FlySpeedCommand;
import de.felix_kurz.craftattackelytrafly.configuration.ConfigManager;
import de.felix_kurz.craftattackelytrafly.listeners.PlayerDamageListener;
import de.felix_kurz.craftattackelytrafly.listeners.PlayerTeleportListener;
import de.felix_kurz.craftattackelytrafly.listeners.PlayerToggleFlightListener;
import de.felix_kurz.craftattackelytrafly.listeners.PlayerToggleGlideEvent;
import de.felix_kurz.craftattackelytrafly.runnables.AllowFlightRunnable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    public ConfigManager cfgM;
    public PlayerToggleFlightListener ptfL = new PlayerToggleFlightListener(this);

    public List<UUID> boostedPlayers = new ArrayList<>();
    public List<UUID> spawnareaPlayers = new ArrayList<>();

    @Override
    public void onEnable() {

        cfgM = new ConfigManager(this);

        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("flyspeed").setExecutor(new FlySpeedCommand());

        Bukkit.getPluginManager().registerEvents(ptfL, this);
        Bukkit.getPluginManager().registerEvents(new PlayerToggleGlideEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerTeleportListener(), this);

        new AllowFlightRunnable(this).run();

    }

}
