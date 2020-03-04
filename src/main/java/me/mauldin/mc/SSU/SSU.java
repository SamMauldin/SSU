package me.mauldin.mc.SSU;

import me.mauldin.mc.SSU.commands.TPACommand;
import me.mauldin.mc.SSU.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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

        // Register all commands
        TPACommand tpacommand = new TPACommand();
        Objects.requireNonNull(this.getCommand("tpa")).setExecutor(tpacommand);
        Objects.requireNonNull(this.getCommand("tpaccept")).setExecutor(tpacommand);
    }

    @Override
    public void onDisable() {

    }


}
