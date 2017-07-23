package me.netyeti.events.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private String welcomeMessage;

    public PlayerJoin(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        String welcomeMsg = ChatColor.translateAlternateColorCodes('&', this.welcomeMessage);
        Player player = event.getPlayer();

        welcomeMsg = welcomeMsg.replace("%p", player.getName());
        player.sendMessage(welcomeMsg);
    }

}
