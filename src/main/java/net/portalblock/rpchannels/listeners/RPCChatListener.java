package net.portalblock.rpchannels.listeners;

import net.portalblock.rpchannels.RPCChannel;
import net.portalblock.rpchannels.RPCManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

/**
 * Created by portalBlock on 27/12/13.
 */
public class RPCChatListener implements Listener {
    RPCManager manager;

    public RPCChatListener() {
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void chat(AsyncPlayerChatEvent e) {
        //Bukkit.broadcastMessage("Event called!");
        String speaker = e.getPlayer().getName();
        if (RPCManager.isMember(speaker)) {
            //  Bukkit.broadcastMessage("You are in a channel!");
            RPCChannel c = RPCManager.getChannelByMember(speaker);
            if (!c.isSpeaking(speaker)) {
                return;
            }
            List<String> members = c.getMembers();
            String msg = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + RPCManager.getChannelByMember(speaker).getOwner() + ChatColor.DARK_GRAY + "] " + ChatColor.BLUE + speaker + ChatColor.WHITE + ": " + ChatColor.RESET + e.getMessage();
            if(members != null){
                for (int i = 0; i < members.size(); i++) {
                    // Bukkit.broadcastMessage(members.get(i));
                    Player p = Bukkit.getPlayerExact(members.get(i));
                    if (p != null) {
                        p.sendMessage(msg);


                    }

                }
            }
            for(Player a : Bukkit.getOnlinePlayers()){
                if(a != null){
                    if(a.hasPermission("rpc.hear")||a.isOp()){
                        if(RPCManager.isHearing(a.getName())){
                            if(RPCManager.isMember(a.getName())){
                                if(!RPCManager.getChannelByMember(a.getName()).getOwner().equals(c.getOwner())){
                                    a.sendMessage(msg);
                                }
                            }else{
                                a.sendMessage(msg);
                            }
                        }
                    }
                }
            }
            e.setCancelled(true);
        } else {
            //  Bukkit.broadcastMessage(ChatColor.DARK_RED+"CHANNEL ALARM!");
            e.setCancelled(false);
        }

    }
}
