package me.mauldin.mc.SSU.listeners;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class NetherTunnelSpawnBlocker implements Listener {

    // Intercept all spawn events for phantoms and cancel them
    @EventHandler
    public void onSpawn(CreatureSpawnEvent ev) {
        if (!ev.getLocation().getWorld().getEnvironment().equals(World.Environment.NETHER)) return;
        double yLevel = ev.getLocation().getY();

        if (yLevel > 135 && yLevel < 145)
            ev.setCancelled(true);
    }
}
