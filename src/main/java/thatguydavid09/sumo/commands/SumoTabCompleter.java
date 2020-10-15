package thatguydavid09.sumo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SumoTabCompleter implements TabCompleter {

    private static List<String> suggestions = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        // FIXME this isn't working
/*        suggestions.add("join");
        suggestions.add("leave");
        return suggestions;
    }*/
        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (args.length < 1) {
                baseChecker(player, "join", "supersumo.sumo.join");
                baseChecker(player, "leave", "supersumo.sumo.leave");
            }
        }
        return suggestions;
    }

    // This checks if user has permission to execute that command and if so, adds it to the list
    private void baseChecker(Player player, String command, String perm) {
        if (player.hasPermission(perm)) {
            suggestions.add(command);
        }
    }
}
