package net.portalblock.rpchannels.commands;

import net.portalblock.rpchannels.RPCChannel;
import net.portalblock.rpchannels.RPCManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Brandon Barker on 1/2/14.
 */
public class RPCGlobalLocalToggle implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (RPCManager.isMember(p.getName())) {
                RPCChannel channel = RPCManager.getChannelByMember(p.getName());
                boolean toggle = channel.toggle(p.getName());
                p.sendMessage(ChatColor.GOLD+"You are now speaking in " + (toggle ? ChatColor.GREEN+"Channel" : ChatColor.GREEN+"Global Chat"));
            }
        }
        return true;
    }
}
