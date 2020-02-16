package me.mauldin.mc.SSU.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;
import java.util.function.Consumer;

import static org.bukkit.Bukkit.getServer;

public class ChatFormatter implements Listener {
    // Change chat format to
    // name: message
    @EventHandler
    public void onChat(AsyncPlayerChatEvent ev) {
        ev.setCancelled(true);
        String displayName = ev.getPlayer().getDisplayName();
        String actualName = ev.getPlayer().getName();
        String msg = ev.getMessage();
        final String chatString = ChatColor.GRAY + displayName + ChatColor.WHITE + ": " + msg;
        final String chatStringBot = ChatColor.GRAY + actualName + ChatColor.WHITE + ": " + msg;

        getServer().getOnlinePlayers().forEach(new Consumer<Player>() {
            @Override
            public void accept(Player player) {
                UUID playerUUID = player.getUniqueId();
                boolean playerIsBot = playerUUID.equals(UUID.fromString("b0e5f122-cd4a-4175-a2e5-78d755e7edb2"));
                player.sendMessage(playerIsBot ? chatStringBot : chatString);
            }
        });
    }
}
