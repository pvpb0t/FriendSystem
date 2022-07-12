package me.pvpb0t.callisto.api.friend;

import net.minecraft.entity.Entity;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
/**
 * @Author pvpb0t
 * @Since 7/12/2022
 */
public class FriendManager {

    private ArrayList<FriendObj> friends = new ArrayList<>();

    public void addFriend(String c, Boolean isUUID){
        FriendObj friendObj = null;
        if(isUUID){
            friendObj = new FriendObj(c);
        }else {
            this.getUuid(c).thenAccept(s->{
                FriendObj fr = new FriendObj(s);
                friends.add(fr);
            });
        }
        if(friendObj!=null){
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
        for(FriendObj friend : friends){
            if(c.equalsIgnoreCase(friend.getUuid())){
                return friend;
            }
        }
        return null;
    }

    public static CompletableFuture<String> getName(String uuid) {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
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
        });
        future.thenAccept(s -> System.out.println("received: " + s));


    return future;}

    public static CompletableFuture<String> getUuid(String name) {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
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
        });
        future.thenAccept(s -> System.out.println("received: " + s));


        return future;}





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
