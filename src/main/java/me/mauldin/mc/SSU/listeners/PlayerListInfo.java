package me.mauldin.mc.SSU.listeners;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.bukkit.Bukkit.getServer;

public class PlayerListInfo implements Listener {

    // Every 20 ticks (one second), update the player list display with TPS info
    @EventHandler
    public void onTick(ServerTickStartEvent ev) {
        if ((ev.getTickNumber() + 5) % 20 == 0) {
            double tps = getServer().getTPS()[0];
            String tpsF = String.format("%.2f", tps);

            for (Player pl : getServer().getOnlinePlayers()) {
                pl.setPlayerListHeader("\n" + ChatColor.WHITE + "     Sam's Vanilla MC Server     \n" +
                        ChatColor.GRAY + tpsF + " tps\n\n ");
                pl.setPlayerListFooter(" ");
            }
        }
    }
}
