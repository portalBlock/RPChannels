package net.portalblock.rpchannels.commands;

import net.portalblock.rpchannels.RPCManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by portalBlock on 2/10/14.
 */
public class RPCHear implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final String prefix = ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"RP"+ChatColor.BLUE+"Channels"+ChatColor.DARK_GRAY+"] ";
            Player p = (Player) sender;
            String pName = p.getName();
            if(p.hasPermission("rpc.hear")){
                if(RPCManager.toggleHearing(p.getName())){
                    p.sendMessage(prefix+ChatColor.BLUE+"Channel spy: "+ChatColor.RED+"ENABLED");
                }else{
                    p.sendMessage(prefix+ChatColor.BLUE+"Channel spy: "+ChatColor.RED+"DISABLED");
                }
            }else{
                p.sendMessage(prefix+ChatColor.RED+"You do not have permission for this action!");
            }
        }
        return true;
    }
}
