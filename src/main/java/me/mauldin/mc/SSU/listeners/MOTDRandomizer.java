package me.mauldin.mc.SSU.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Random;

public class MOTDRandomizer implements Listener {

    // Give a random MOTD message for the server list on every refresh
    @EventHandler
    public void onPing(ServerListPingEvent ev) {
        String[] messages = {
                "lol",
                "good meme",
                "send coords",
                "diamonds, i have. craft, i will.",
                "I nEeD a DuPe MeThOd",
                "now owned by microsoft",
                "we have diamonds, we have diamonds",
                "better than fortnite",
                "3d terraria",
                "no digital miner",
                "32k dragon egg",
                "hours > deathcount",
                ChatColor.MAGIC + "you have discovered ancient wisdom",
                ChatColor.LIGHT_PURPLE + "PURPLE",
                "block fact 1: grass spreads to dirt",
                "block fact 2: sand and gravel fall down",
                "block fact 3: blocks are cuboids",
                "block fact 5:" + ChatColor.MAGIC + "AAAAAAAAA",
                "have you heard the tale of darth plageous the wise?",
                "in west philadelpia born and raised",
                "salacious crumb",
                "JASON!",
                "oof",
                "got the green top",
                "It is inevitable.",
                "2b2t is full",
                "water > pepsi > coke",
                "going to college in 2020",
                "just kidding... unless?",
                "minecraft in 2020",
                "block facts will return next week",
                "block fact 6: blocks are made out of smaller blocks"
        };

        Random rand = new Random();
        // nextInt(upperBound) is exclusive, so it will not overflow
        String msg = messages[rand.nextInt(messages.length)];

        ev.setMotd(ChatColor.AQUA + "Sam's Vanilla Minecraft\n" + ChatColor.RESET + msg);
    }
}
