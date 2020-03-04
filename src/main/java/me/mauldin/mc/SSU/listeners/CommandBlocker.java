package me.mauldin.mc.SSU.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashSet;
import java.util.UUID;

public class CommandBlocker implements Listener {
    // Don't allow any non-SSU commands
    // The console does not fire this event and can still use commands
    // Allow bypass for me

    private final static HashSet<String> allowedCommands = new HashSet<String>(){{
        add("/tpa");
        add("/tpaccept");
    }};

    private final static HashSet<UUID> bypassList = new HashSet<UUID>(){{
        add(UUID.fromString("91980b5d-9b98-45e8-843d-4da2c0436cf3"));
    }};

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent ev) {
        String command = ev.getMessage().toLowerCase().split(" ")[0];

        if (allowedCommands.contains(command)) return;
        if (bypassList.contains(ev.getPlayer().getUniqueId())) return;

        ev.setCancelled(true);
        ev.getPlayer().sendMessage(ChatColor.RED + "Commands are disabled!");
    }
}
