package me.mauldin.mc.SSU.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandBlocker implements Listener {
    // Don't allow any commands
    // The console does not fire this event and can still use commands
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent ev) {
        ev.setCancelled(true);
        ev.getPlayer().sendMessage(ChatColor.RED + "Commands are disabled!");
    }
}
