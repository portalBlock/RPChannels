package net.portalblock.rpchannels;

import net.portalblock.rpchannels.commands.*;
import net.portalblock.rpchannels.listeners.RPCChatListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class RPChannels extends JavaPlugin {
    //public HashMap<String, String> groups = new HashMap<String, String>();
    public File dir;

    @Override
    public void onEnable() {
        dir = new File(getDataFolder().getPath(), "channels");
        reg();
        //getLogger().info("RPChannels has been enabled!");
        getServer().getPluginManager().registerEvents(new RPCChatListener(), this);
        recreateChannels();
        //getLogger().info(getDataFolder().getPath());
    }

    public void onDisable() {
        RPCManager.saveChannels(dir);
        RPCManager.reset();
    }


    public void reg() {
        getCommand("rpccreate").setExecutor(new RPCCreate());
        //getCommand("rpcadd").setExecutor(new RPCAdd());
        getCommand("rpcallow").setExecutor(new RPCAllow());
        getCommand("rpcjoin").setExecutor(new RPCJoin());
        getCommand("rpcleave").setExecutor(new RPCLeave());
        getCommand("rpchelp").setExecutor(new RPCHelp());
        getCommand("rpctog").setExecutor(new RPCGlobalLocalToggle());
        getCommand("rpchear").setExecutor(new RPCHear());
    }

    public void recreateChannels() {
        dir.mkdirs();
            for (File file : dir.listFiles()) {
                RPCChannel.fromConfig(YamlConfiguration.loadConfiguration(file));
            }
    }
}