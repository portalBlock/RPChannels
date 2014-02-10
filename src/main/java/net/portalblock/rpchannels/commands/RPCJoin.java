package net.portalblock.rpchannels.commands;

import net.portalblock.rpchannels.RPCManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RPCJoin implements CommandExecutor {
    public RPCJoin() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!RPCManager.isChannel(p.getName()) && !RPCManager.isMember(p.getName())) {
                if (args.length >= 1) {
                    if (RPCManager.isChannel(args[0])) {
                        Player r = Bukkit.getPlayerExact(args[0]);
                        if (r != null) {
                            RPCManager.addRequest(p.getName(), args[0]);
                            p.sendMessage(ChatColor.GOLD+"A request has been sent.");
                            r.sendMessage(ChatColor.GREEN+p.getName() + ChatColor.GOLD+" has requested to join your channel.");
                            r.sendMessage(ChatColor.GOLD+"Use /rpcallow "+ChatColor.GREEN + p.getName() +ChatColor.GOLD+ " to add them.");
                        } else {
                            p.sendMessage(ChatColor.GOLD+"The owner is not online, please wait to request.");
                        }
                    } else {
                        p.sendMessage(ChatColor.GOLD+"That is not a valid channel.");
                    }
                } else {
                    p.sendMessage(ChatColor.RED+"Please include the channel you would like to join.");
                    p.sendMessage(ChatColor.GOLD+"/rpcjoin <name>");
                }
            }

        }
        return true;
    }
}
