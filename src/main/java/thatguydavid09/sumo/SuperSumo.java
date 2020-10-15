package thatguydavid09.sumo;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import thatguydavid09.sumo.commands.SumoCommand;
import thatguydavid09.sumo.commands.SumoTabCompleter;

import java.util.ArrayList;
import java.util.List;

public final class SuperSumo extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("sumo").setExecutor(new SumoCommand());
        this.getCommand("sumo").setTabCompleter(new SumoTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
