package me.mauldin.mc.SSU;

import me.mauldin.mc.SSU.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

// Sam's Server Utilities
public class SSU extends JavaPlugin {
    @Override
    public void onEnable() {

        // Register all events
        getServer().getPluginManager().registerEvents(new NameAssigner(), this);
        getServer().getPluginManager().registerEvents(new ChatFormatter(), this);
        getServer().getPluginManager().registerEvents(new CommandBlocker(), this);
        getServer().getPluginManager().registerEvents(new PhantomRemover(), this);
        getServer().getPluginManager().registerEvents(new MOTDRandomizer(), this);
        getServer().getPluginManager().registerEvents(new PlayerListInfo(), this);
    }

    @Override
    public void onDisable() {

    }


}
