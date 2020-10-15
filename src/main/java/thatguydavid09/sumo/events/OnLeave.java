package thatguydavid09.sumo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import thatguydavid09.sumo.commands.SumoCommand;

public class OnLeave implements Listener {

    // Remove players from sumo event if they leave server and TODO update bracket
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (SumoCommand.playersInSumo.contains(event.getPlayer().getUniqueId())) {
            SumoCommand.playersInSumo.remove(event.getPlayer().getUniqueId());
        }
    }
}
