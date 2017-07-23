package me.netyeti.events.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        String message = event.getMessage().toLowerCase();

        if(message.contains("brandon")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.LIGHT_PURPLE + "I wear pink panties.");
        }

    }
}
