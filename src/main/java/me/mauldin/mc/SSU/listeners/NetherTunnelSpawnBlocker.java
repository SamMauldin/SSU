package me.mauldin.mc.SSU.listeners;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class NetherTunnelSpawnBlocker implements Listener {

    private static final Set<EntityType> ENTITY_TYPES = Collections.unmodifiableSet(EnumSet.of(EntityType.PIG_ZOMBIE, EntityType.GHAST, EntityType.SMALL_FIREBALL));

    // Intercept all spawn events for phantoms and cancel them
    @EventHandler
    public void onSpawn(CreatureSpawnEvent ev) {
        if (!ev.getLocation().getWorld().getEnvironment().equals(World.Environment.NETHER)) return;
        double yLevel = ev.getLocation().getY();

        if (!ENTITY_TYPES.contains(ev.getEntityType())) return;

        if (yLevel > 135 && yLevel < 145)
            ev.setCancelled(true);
    }
}
