package me.netyeti.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Equip implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command.");
            return false;
        }

//        Player player = (Player)sender;
//
//        PlayerInventory inv = player.getInventory();
//        inv.clear(); // clear the inventory
//
//        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
//        ItemStack stone = new ItemStack(Material.STONE, 32);
//        ItemStack bow = new ItemStack(Material.BOW);
//        ItemStack arrows = new ItemStack(Material.ARROW, 100);
//        ItemStack leatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
//
//        inv.addItem(
//                diamondSword, stone, bow, arrows, leatherChestplate
//        );
//
//        leatherChestplate.addUnsafeEnchantment(Enchantment.DIG_SPEED, 55);
//        bow.addEnchantment(Enchantment.ARROW_FIRE, 40);
//        arrows.addEnchantment(Enchantment.ARROW_INFINITE, 40);
//
//        inv.setChestplate(leatherChestplate);
//        inv.setItemInMainHand(bow);
//
//        player.sendMessage("Your inventory has been changed.");
        return true;
    }
}
