package me.pvpb0t.callisto.api.friend;

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
