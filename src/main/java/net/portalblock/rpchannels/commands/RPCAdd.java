package net.portalblock.rpchannels.commands;

import net.portalblock.rpchannels.RPCManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by portalBlock on 28/12/13.
 */
public class RPCAdd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String pName = p.getName();
            if (RPCManager.isChannel(pName)) {
                if (args[0].length() >= 1) {
                    if (!RPCManager.isMember(args[0])) {
                        RPCManager.addMember(pName, args[0]);
                        p.sendMessage(ChatColor.GOLD + args[0] + ChatColor.GREEN + " has been added to your channel.");
                    } else {
                        p.sendMessage(ChatColor.GOLD+"This person is already in a channel, ask them to leave.");
                    }
                } else {
                    p.sendMessage(ChatColor.GOLD+"Please include a player name!");
                    p.sendMessage(ChatColor.GREEN+"/rpcadd <name>");
                }
            } else {
                p.sendMessage(ChatColor.RED+"You do not have a channel to add people to!");
                p.sendMessage(ChatColor.GOLD+"/rpccreate");
            }
        }
        return true;
    }
}
