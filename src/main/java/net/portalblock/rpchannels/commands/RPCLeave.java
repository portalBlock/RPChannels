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

public class RPCLeave implements CommandExecutor {
    public RPCLeave() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (RPCManager.isOwner(p.getName())) {
                List<String> members = RPCManager.getChannelByMember(p.getName()).getMembers();
                for (int i = 0; i < members.size(); i++) {
                    // Bukkit.broadcastMessage(members.get(i));/
                    Player pl = Bukkit.getPlayerExact(members.get(i));
                    if (pl != null) {
                        pl.sendMessage(ChatColor.RED+"The channel you are in has been deleted.");
                        RPCManager.delMember(p.getName(), pl.getName());
                    }

                }
                RPCManager.delChannel(p.getName());
                p.sendMessage(ChatColor.RED+"You have deleted the channel");
            } else if (RPCManager.isMember(p.getName())) {
                RPCChannel channel = RPCManager.getChannelByMember(p.getName());
                RPCManager.delMember(channel.getOwner(), p.getName());
                p.sendMessage(ChatColor.RED+"You have left the channel.");
            }
        }
        return true;
    }
}
