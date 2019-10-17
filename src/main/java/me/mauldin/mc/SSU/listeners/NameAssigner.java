package me.mauldin.mc.SSU.listeners;

import me.mauldin.mc.SSU.util.NameMapper;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NameAssigner implements Listener {
    // On player join, set their display and list name from the map
    // Modify join message to use the new name
    @EventHandler
    public void onJoin(PlayerJoinEvent ev) {
        String name = NameMapper.getName(ev.getPlayer());

        ev.setJoinMessage(ChatColor.YELLOW + name + ChatColor.YELLOW + " joined the game");

        ev.getPlayer().setDisplayName(name);
        ev.getPlayer().setPlayerListName(name);
    }

    // Modify leave message to use player display name
    @EventHandler
    public void onLeave(PlayerQuitEvent ev) {
        ev.setQuitMessage(ChatColor.YELLOW + ev.getPlayer().getDisplayName() + ChatColor.YELLOW + " left the game");
    }
}
