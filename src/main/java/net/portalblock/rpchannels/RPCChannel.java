package net.portalblock.rpchannels;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RPCChannel {
    private String owner;
    private Map<String, Boolean> members;

    public RPCChannel(String owner) {
        this.owner = owner;
        members = new HashMap<String, Boolean>();
        members.put(owner, false);
    }

    public String getOwner() {
        return owner;
    }

    public boolean isOwner(String owner) {
        return this.owner.equalsIgnoreCase(owner);
    }

    public boolean addMember(String member) {
        if (isMember(member)) {
            return false;
        }
        members.put(member, false);
        return true;
    }

    public boolean isMember(String member) {
        return members.containsKey(member);
    }

    public ArrayList<String> getMembers() {
        //String[] array = members.toArray(new String[members.size()]);
        if(!members.isEmpty()){
             return new ArrayList(members.keySet());
        }else{
            return null;
        }
    }

    public boolean delMember(String member) {
        if (!isMember(member)) {
            return false;
        }
        members.remove(member);
        return true;
    }

    public boolean isSpeaking(String member) {
        if (!isMember(member)) {
            return false;
        }
        return members.get(member);
    }

    public boolean toggle(String member) {
        if (!isMember(member)) {
            return false;
        }
        boolean bool = !members.get(member);
        members.put(member, bool);
        return bool;
    }

    public void save(File file) throws IOException {
        File newFile = new File(file, owner + ".yml");
        newFile.getParentFile().mkdirs();
        newFile.createNewFile();
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.set("Owner", owner);
        yamlConfiguration.set("Members", members);
        yamlConfiguration.save(newFile);
    }

    public static void fromConfig(YamlConfiguration yamlConfiguration) {
        String owner = yamlConfiguration.getString("Owner", "Error");
        List<String> members = yamlConfiguration.getStringList("Members");
        members = members == null ? new ArrayList<String>() : members;
        RPCManager.remakeChannel(owner, members);
    }

}
