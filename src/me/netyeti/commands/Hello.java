package me.netyeti.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Hello implements CommandExecutor {

    private List<String> serverAdmins;

    public Hello(List<String> serverAdmins) {
        this.serverAdmins = serverAdmins;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command.");
            return false;
        }

        Player player = (Player)sender;

        player.sendMessage("The server admins are:");
        for(String admin : serverAdmins) {
            player.sendMessage("- " + admin);
        }

        return true;
    }
}
