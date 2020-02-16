package me.mauldin.mc.SSU.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

// Store and retrieve nicknames

public class NameMapper {
    private final static HashMap<UUID, String> nameMap = new HashMap<UUID, String>(){{
        put(UUID.fromString("91980b5d-9b98-45e8-843d-4da2c0436cf3"), ChatColor.AQUA + "Sam");
        put(UUID.fromString("b0e5f122-cd4a-4175-a2e5-78d755e7edb2"), ChatColor.LIGHT_PURPLE + "Aurelius Bot");
        put(UUID.fromString("eb450644-cf49-44a6-b954-289627af49f5"), ChatColor.DARK_RED + "Fit");
        put(UUID.fromString("24ebe834-9728-4b4a-a7ca-96384519a292"), ChatColor.BLUE + "SetsunaPlus");
        put(UUID.fromString("96b136d8-6023-4459-a6b7-fd151eddddf7"), ChatColor.WHITE + "pop" + ChatColor.BLACK + "bob");
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
