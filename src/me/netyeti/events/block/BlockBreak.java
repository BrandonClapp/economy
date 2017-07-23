package me.netyeti.events.block;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        // player can only break if they are op.
        if(!player.isOp()) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You can only break grass!");
        }
    }

}
