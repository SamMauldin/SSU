package me.mauldin.mc.SSU.commands;

import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

import static me.mauldin.mc.SSU.util.NameMapper.resolveOnlinePlayer;
import static org.bukkit.Bukkit.getServer;

public class TPACommand implements CommandExecutor {

    private final long COOLDOWN_MINUTES = 15;
    private final long COOLDOWN_MS = 1000 * 60 * COOLDOWN_MINUTES;

    HashMap<UUID, Long> cooldownMap = new HashMap<UUID, Long>();
    HashMap<UUID, UUID> requestMap = new HashMap<UUID, UUID>();

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(String.format("%sCommand cannot be executed on the console", ChatColor.RED));
            return true;
        }

        Player senderPlayer = (Player) sender;
        UUID senderUUID = senderPlayer.getUniqueId();

        if (cmd.getName().equalsIgnoreCase("tpa")) {
            if (args.length != 1) return false;

            Player targetPlayer = resolveOnlinePlayer(args[0]);

            if (targetPlayer == null) {
                sender.sendMessage(String.format("%sCould not find target player!", ChatColor.RED));
                return true;
            }

            UUID targetUUID = targetPlayer.getUniqueId();

            if (targetUUID.equals(senderUUID)) {
                sender.sendMessage(String.format("%sYou cannot teleport to yourself!", ChatColor.RED));
                return true;
            }

            if (cooldownMap.containsKey(senderUUID)) {
                long diff = System.currentTimeMillis() - cooldownMap.get(senderUUID);
                if (diff < COOLDOWN_MS) {
                    sender.sendMessage(String.format("%sYou must wait %d minutes before teleporting again.", ChatColor.RED, COOLDOWN_MINUTES));
                    return true;
                }
            }

            requestMap.put(targetUUID, senderUUID);

            sender.sendMessage(String.format("%sRequest sent to %s%s!", ChatColor.GREEN, targetPlayer.getDisplayName(), ChatColor.GREEN));

            boolean targetIsBot = targetUUID.equals(UUID.fromString("b0e5f122-cd4a-4175-a2e5-78d755e7edb2"));
            String senderDisplayName = targetIsBot ? senderPlayer.getName() : senderPlayer.getDisplayName();
            targetPlayer.sendMessage(String.format("%sYou have a teleport request from %s%s! Type '/tpaccept %s%s' to accept.", ChatColor.AQUA, senderDisplayName,  ChatColor.AQUA, senderDisplayName, ChatColor.AQUA));

            return true;
        } else if (cmd.getName().equalsIgnoreCase("tpaccept")) {
            if (args.length != 1) return false;

            if (requestMap.containsKey(senderUUID)) {
                UUID teleportUUID = requestMap.get(senderUUID);

                Player targetPlayer = resolveOnlinePlayer(args[0]);

                if (targetPlayer == null) {
                    sender.sendMessage(String.format("%sCould not find target player!", ChatColor.RED));
                    return true;
                }

                if (!targetPlayer.getUniqueId().equals(teleportUUID)) {
                    sender.sendMessage(String.format("%sCould not find teleport request from %s%s!", ChatColor.RED, targetPlayer.getDisplayName(), ChatColor.RED));
                    return true;
                }

                Player teleportPlayer = getServer().getPlayer(teleportUUID);

                if (teleportPlayer == null) {
                    sender.sendMessage(String.format("%sCould not find target player!", ChatColor.RED));
                    return true;
                }

                if (cooldownMap.containsKey(teleportUUID)) {
                    long diff = System.currentTimeMillis() - cooldownMap.get(teleportUUID);
                    if (diff < COOLDOWN_MS) {
                        sender.sendMessage(String.format("%sYou have no teleport requests to accept!", ChatColor.RED));
                        return true;
                    }
                }

                cooldownMap.put(teleportUUID, System.currentTimeMillis());

                teleportPlayer.teleport(senderPlayer);

                sender.sendMessage(String.format("%sTeleport request accepted!", ChatColor.GREEN));
                teleportPlayer.sendMessage(String.format("%sTeleport request accepted!", ChatColor.GREEN));

            } else {
                sender.sendMessage(String.format("%sYou have no teleport requests to accept!", ChatColor.RED));
            }
            return true;
        }

        return false;
    }
}
