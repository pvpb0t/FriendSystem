package me.pvpb0t.callisto.api.friend;
/**
 * @Author pvpb0t
 * @Since 7/12/2022
 */
public class FriendObj {

    private String uuid;

    public FriendObj(){}

    public FriendObj(String uuid){
        this.uuid = uuid;
    }

    public void setUUID (String uuid){
        this.uuid = uuid;
    }
    public String getUuid() {
        return this.uuid;
    }
}
