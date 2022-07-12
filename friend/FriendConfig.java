package me.pvpb0t.callisto.api.friend;

import me.pvpb0t.callisto.Callisto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
/**
 * @Author pvpb0t
 * @Since 7/12/2022
 */
public class FriendConfig extends Thread{

    static FriendManager friendManager = Callisto.getCallisto().getFriendManager();
    static String path = Callisto.mainFolder + File.separator +  "friends.json";

    @Override
    public void run(){
        saveFriendsToJSON();
    }

    public static void loadFriendsFromJSON() {
        JSONParser parser = new JSONParser();
        ArrayList<FriendObj> loadedFriends = new ArrayList<>();
        FriendObj friend = new FriendObj();
        String fileName = path;
        if(new File(path).exists()) {

            try {

                Object obj = parser.parse(new FileReader(fileName));
                JSONObject jsonObject = (JSONObject) obj;

                JSONArray UUIDs = (JSONArray) jsonObject.get("UUID");
                for (Object uuid : UUIDs) {
                    friend.setUUID((String) uuid);
                    loadedFriends.add(friend);
                }

            } catch (FileNotFoundException e) {
                System.err.println("ERROR : FILE NOT FOUND.");
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
            if (!loadedFriends.isEmpty()) {
                friendManager.setFriends(loadedFriends);
            }
        }
    }

    public void saveFriendsToJSON(){
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();

        for(FriendObj friend : friendManager.getFriends()){
            array.add(friend.getUuid());
        }
        jsonObject.put("UUID", array);
        try{
            FileWriter file = new FileWriter(path);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
