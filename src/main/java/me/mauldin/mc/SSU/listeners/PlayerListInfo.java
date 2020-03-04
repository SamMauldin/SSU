package me.mauldin.mc.SSU.listeners;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.bukkit.Bukkit.getServer;

public class PlayerListInfo implements Listener {

    final Instant startTime = Instant.now();

    final String pattern = "yyyy-MM-dd";
    final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

    // Every 20 ticks (one second), update the player list display with TPS info
    @EventHandler
    public void onTick(ServerTickStartEvent ev) {
        if ((ev.getTickNumber() + 5) % 20 == 0) {
            double tps = getServer().getTPS()[0];
            String tpsF = String.format("%.2f", tps);
            Instant now = Instant.now();
            Duration uptime = Duration.between(startTime, now);
            Duration uptimeRounded = Duration.ofMillis(1000 * (uptime.toMillis() / 1000));

            String formattedUptime = uptimeRounded.toString()
                    .substring(2)
                    .replaceAll("(\\d[HMSD])(?!$)", "$1 ")
                    .toLowerCase();

            for (Player pl : getServer().getOnlinePlayers()) {
                Date firstPlayed = new Date(pl.getFirstPlayed());
                String firstPlayedFormatted = dateFormat.format(firstPlayed);

                pl.setPlayerListHeader("\n" + ChatColor.WHITE + "     Sam's Vanilla MC Server     \n" +
                        ChatColor.GRAY + "\nFirst Played " + firstPlayedFormatted + "\n ");
                pl.setPlayerListFooter("\n" + ChatColor.GRAY + tpsF + " tps - Uptime: " + formattedUptime + "\n \n");
            }
        }
    }
}
