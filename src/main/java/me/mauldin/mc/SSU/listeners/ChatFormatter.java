package me.mauldin.mc.SSU.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatter implements Listener {
    // Change chat format to
    // name: message
    @EventHandler
    public void onChat(AsyncPlayerChatEvent ev) {
        String name = ev.getPlayer().getDisplayName();
        String msg = ev.getMessage();
        ev.setFormat(ChatColor.GRAY + name + ChatColor.WHITE + ": " + msg);
    }
}
