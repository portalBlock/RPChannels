package net.portalblock.rpchannels.commands;

import net.portalblock.rpchannels.RPCManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RPCAllow implements CommandExecutor {
    public RPCAllow() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (RPCManager.isChannel(p.getName())) {
                if (args.length >= 1) {
                    if (RPCManager.requestCheck(p.getName(), args[0])) {
                        p.sendMessage(ChatColor.GOLD + args[0] + ChatColor.WHITE + " has been added to your channel.");
                        Player j = Bukkit.getPlayerExact(args[0]);
                        if (j != null) {
                            j.sendMessage(ChatColor.GOLD+"You have been added to "+ChatColor.GREEN + p.getName() +ChatColor.GOLD+ "'s channel.");
                            j.sendMessage(ChatColor.GOLD+"Use /rpctog to toggle between channel and global chat.");
                        }
                    } else {
                        p.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " has not requested to be added.");
                    }
                } else {
                    p.sendMessage(ChatColor.GOLD+"Please include the user you want to add.");
                    p.sendMessage(ChatColor.RED+"/rpcallow <name>");
                }
            } else {
                p.sendMessage(ChatColor.GOLD+"You do not have a channel.");
                p.sendMessage(ChatColor.RED+"Use /rpccreate to make one.");
            }
        }
        return true;
    }

}
