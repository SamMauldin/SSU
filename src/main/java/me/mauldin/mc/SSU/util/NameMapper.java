package me.mauldin.mc.SSU.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

// Store and retrieve nicknames

public class NameMapper {
    private final static HashMap<UUID, String> nameMap = new HashMap<UUID, String>(){{
        put(UUID.fromString("91980b5d-9b98-45e8-843d-4da2c0436cf3"), ChatColor.AQUA + "Sam");
    }};

    public static String getName(Player pl) {
        UUID uuid = pl.getUniqueId();

        if (nameMap.containsKey(uuid)) {
            return nameMap.get(uuid);
        } else {
            return pl.getName();
        }
    }
}
