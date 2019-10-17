package me.mauldin.mc.SSU.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PhantomRemover implements Listener {

    // Intercept all spawn events for phantoms and cancel them
    @EventHandler
    public void onSpawn(CreatureSpawnEvent ev) {
        if (ev.getEntityType().equals(EntityType.PHANTOM)) {
            ev.setCancelled(true);
        }
    }
}
