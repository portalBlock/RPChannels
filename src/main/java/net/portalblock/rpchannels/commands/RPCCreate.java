package net.portalblock.rpchannels.commands;

import net.portalblock.rpchannels.RPCManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RPCCreate implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String senderName = sender.getName();
            if (!RPCManager.isChannel(senderName)) {
                RPCManager.makeChannel(senderName);
                p.sendMessage(ChatColor.GOLD+"Your channel has been created.");
                p.sendMessage(ChatColor.BLUE+"Use /rpcallow <name> to add people to the channel after they have requested to join.");
                p.sendMessage(ChatColor.GOLD+"Use /rpctog to toggle between channel and global chat.");
            } else {
                p.sendMessage(ChatColor.GOLD + "You already have a channel!");
            }
            return true;
        }
        return true;
    }

}
