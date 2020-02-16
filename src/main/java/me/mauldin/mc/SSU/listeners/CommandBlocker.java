package me.mauldin.mc.SSU.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashSet;

public class CommandBlocker implements Listener {
    // Don't allow any non-SSU commands
    // The console does not fire this event and can still use commands

    private final static HashSet<String> allowedCommands = new HashSet<String>(){{
        add("/tpa");
        add("/tpaccept");
    }};

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent ev) {
        String command = ev.getMessage().toLowerCase().split(" ")[0];

        if (allowedCommands.contains(command)) return;

        ev.setCancelled(true);
        ev.getPlayer().sendMessage(ChatColor.RED + "Commands are disabled!");
    }
}
