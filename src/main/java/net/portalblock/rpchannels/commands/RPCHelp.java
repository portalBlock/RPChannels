package net.portalblock.rpchannels.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by portalBlock on 28/12/13.
 */
public class RPCHelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ChatColor.DARK_GREEN+"-----------------------------------------------------");
        sender.sendMessage(ChatColor.GOLD+"/rpccreate - Makes a channel with your name.");
        sender.sendMessage(ChatColor.GOLD+"/rpcjoin <name> (case sensitive) - Sends a join request.");
        sender.sendMessage(ChatColor.GOLD+"/rpcallow <name> (case sensitive) - Accepts a join request.");
        sender.sendMessage(ChatColor.GOLD+"/rpcleave - Leaves the current channel. (deletes channel if owner)");
        sender.sendMessage(ChatColor.RED+"/rpctog - Toggle between local and global chat.");
        sender.sendMessage(ChatColor.DARK_GREEN+"-----------------------------------------------------");
        return true;
    }
}
