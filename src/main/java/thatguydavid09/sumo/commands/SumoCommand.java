package thatguydavid09.sumo.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SumoCommand implements CommandExecutor {

    public static List<UUID> playersInSumo = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        // Do failure checks
        // Enforce one argument
        if (args.length < 1) {
            commandSender.sendMessage("You should have at least 1 argument!");
        }

        if (commandSender instanceof Player) {
            // Branch off to player commands
            playerCommands(commandSender, args);
            return true;
        }

        // Console commands
        consoleAndPlayerCommands(commandSender, args);
        return true;
    }

    private void playerCommands(CommandSender commandSender, String[] args) {

        Player player = (Player) commandSender;
        // Check for join command and check perms
        if (args[0].equals("join")) {
            if (verifyPerm(player, "supersumo.sumo.join", args, 1).isEmpty()) {
                joinSumo(player);
            }
        } else if (args[0].equals("leave")) {
            if (verifyPerm(player, "supersumo.sumo.leave", args, 1).isEmpty()) {
                leaveSumo(player);
            }
        }
    }

    private void consoleAndPlayerCommands(CommandSender commandSender, String[] args) {
    }

    private void joinSumo(Player player) {

        if (playersInSumo.contains(player.getUniqueId())) {
            sendFail(player, "You are already in the sumo event!");
        } else {
            // Add player to sumo and confirm
            playersInSumo.add(player.getUniqueId());
            sendSuccess(player, "You have been added to the sumo event!");
        }
    }

    private void leaveSumo(Player player) {
        if (!playersInSumo.contains(player.getUniqueId())) {
            sendFail(player, "You aren't in the sumo event!");
        } else {
            // Add player to sumo and confirm
            playersInSumo.remove(player.getUniqueId());
            sendSuccess(player, "You have been removed from the sumo event!");
        }
    }

    private void sendFail(Player player, String message) {
        player.sendMessage(ChatColor.RED + message);
        // FIXME WHY DOESN"T THIS COMPILE IF UNCOMMENTED?
        // player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }

    private void sendSuccess(Player player, String message) {
        player.sendMessage(ChatColor.GREEN + message);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }

    // Verifies that player has permissions and arg length is correct
    private String verifyPerm(Player player, String perm, String[] args, int argLength) {
        if (player.hasPermission(perm)) {
            if (args.length == argLength) {
                return "";
            } else {
                return String.format("You need %1s arguments to do this!", argLength);
            }
        }

        return "You don'y have the permissions to do that!";
    }
}
