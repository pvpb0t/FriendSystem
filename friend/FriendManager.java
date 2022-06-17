package me.pvpb0t.callisto.api.friend;

import net.minecraft.entity.Entity;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FriendManager {

    private ArrayList<FriendObj> friends = new ArrayList<>();

    public void addFriend(String c, Boolean isUUID){
        if(isUUID){
            final FriendObj friendObj = new FriendObj(c);
            friends.add(friendObj);
        }else {
            final FriendObj friendObj = new FriendObj(this.getUuid(c));
            friends.add(friendObj);
        }
    }

    public boolean isFriend(String c){
            for(FriendObj friendObj : friends){
                if(c.equalsIgnoreCase(friendObj.getUuid())){
                    return true;
                }
            }

    return false;}

    public Boolean isFriend(Entity e) {


        Boolean isFriend = this.isFriend(e.getUniqueID().toString().replace("-", ""));

        return isFriend;
    }

    public FriendObj getFriend(String c){
            for(FriendObj friendz : friends){
                if(c.equalsIgnoreCase(friendz.getUuid())){
                    return friendz;
                }
            }
    return null;}

    public String getName(String uuid) {
        String url = "https://api.mojang.com/user/profiles/"+uuid.replace("-", "")+"/names";
        try {
            String nameJson = IOUtils.toString(new URL(url));
            JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
            String playerSlot = nameValue.get(nameValue.size()-1).toString();
            JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
            return nameObject.get("name").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "invalid name";
    }

    public String getUuid(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/"+name;
        try {
            String UUIDJson = IOUtils.toString(new URL(url));
            if(UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            return UUIDObject.get("id").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "invalid name";
    }

    public void unload(){
        friends.clear();
    }

    public ArrayList<FriendObj> getFriends(){
        return friends;
    }

    public void setFriends(ArrayList<FriendObj> friendObjArrayList){
        this.friends = friendObjArrayList;
    }




}
