package me.netyeti;

import me.netyeti.commands.Equip;
import me.netyeti.commands.Hello;
import me.netyeti.events.block.BlockBreak;
import me.netyeti.events.player.PlayerChat;
import me.netyeti.events.player.PlayerJoin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class NetyetiPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println("(!) The test plugin works.");

        registerConfig();
        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {

    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new PlayerChat(), this);

        String welcomeMsg = getConfig().getString("Welcome Message");
        pm.registerEvents(new PlayerJoin(welcomeMsg), this);
    }

    public void registerCommands() {

        List<String> serverAdmins = getConfig().getStringList("Server Admins");

        getCommand("hello").setExecutor(new Hello(serverAdmins));
        getCommand("equip").setExecutor(new Equip());
    }

    public void registerConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}
