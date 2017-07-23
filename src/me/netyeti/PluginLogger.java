package me.netyeti;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class PluginLogger {
    private JavaPlugin plugin;
    private Logger log;

    public PluginLogger(JavaPlugin plugin) {
        this.plugin = plugin;
        this.log = Logger.getLogger("TreeLogger");
    }

    private String getMessage(String message) {
        PluginDescriptionFile pdf = plugin.getDescription();
        return "[" + pdf.getName() + " : " + pdf.getVersion() + "] " + message;
    }

    public void info(String message) {
        this.log.info(this.getMessage(message));
    }
}
