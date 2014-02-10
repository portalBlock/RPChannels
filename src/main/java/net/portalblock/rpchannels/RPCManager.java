package net.portalblock.rpchannels;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by portalBlock on 27/12/13.
 */
public class RPCManager {
    private static HashMap<String, RPCChannel> channels = new HashMap<String, RPCChannel>();
    private static HashMap<String, String> requests = new HashMap<String, String>();//<Name, channel>
    private static List<String> staff = new ArrayList<String>();

    private RPCManager() {

    }

    public static void makeChannel(String owner) {
        channels.put(owner, new RPCChannel(owner));
    }

    public static void reset(){
        channels.clear();
        requests.clear();
    }
    public static void remakeChannel(String owner, Collection<String> members) {
        System.out.print("Loaded Channel: " + owner + ". With " + members.size() + " members.");
        channels.put(owner, new RPCChannel(owner));
        //areMembers.put(owner, owner);
        for (String member : members) {
            addMember(owner, member);
        }
    }

    public static void saveChannels(File file) {
        file.mkdirs();
        for (File f : file.listFiles()) {
            f.delete();
        }

        for (RPCChannel channel : getChannels()) {
            try {
                channel.save(file);
            } catch (Exception e) {
                System.out.print("Failed to save: " + channel.getOwner());
            }
        }

    }

    public static boolean isMember(String name) {
        for (RPCChannel channel : getChannels()) {
            if (channel.isMember(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOwner(String name) {
        for (RPCChannel channel : getChannels()) {
            if (channel.isOwner(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean addMember(String channel, String name) {
        //if (!isMember(name)) {
        //return false;
        // }
        //areMembers.put(name, channel);/
        return channels.get(channel).addMember(name);
        //return true;
    }

    public static boolean isChannel(String name) {
        return (channels.containsKey(name));
    }

    public static List<String> getMembers(String channel) {
        if (channels.containsKey(channel)) {
            //List<String> members = channels.get(channel).getMembers();
            return channels.get(channel).getMembers();
        } else {
            return null;
        }
    }

    public static RPCChannel getChannelByMember(String name) {
        for (RPCChannel channel : getChannels()) {
            if (channel.isMember(name)) {
                return channel;
            }
        }
        return null;
    }

    public static void addRequest(String name, String channel) {
        if (!requests.containsKey(name)) {
            requests.put(name, channel);
        } else {
            requests.remove(name);
            requests.put(name, channel);
        }
    }

    public static boolean requestCheck(String channel, String name) {
        if (requests.containsKey(name)) {
            if (requests.get(name).equals(channel)) {
                addMember(channel, name);
                requests.remove(name);
                return true;
            }
        }
        return false;
    }

    public static void delMember(String channel, String name) {
        RPCChannel channel1 = channels.get(channel);
        if (channel1 == null) {
            return;
        }
        channel1.delMember(name);
        if (channel1.getOwner().equalsIgnoreCase(name)) {
            channels.remove(channel);
        }
    }

    public static void delChannel(String channel) {
        channels.remove(channel);
    }

    public static Collection<RPCChannel> getChannels() {
        return channels.values();
    }

    public static boolean isHearing(String name){
        return staff.contains(name);
    }

    public static boolean toggleHearing(String name){
        if(staff.contains(name)){
            staff.remove(name);
            return false;
        }else{
            staff.add(name);
            return true;
        }
    }
}
