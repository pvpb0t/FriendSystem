package me.pvpb0t.callisto.impl.command;

import me.pvpb0t.callisto.Callisto;
import me.pvpb0t.callisto.api.command.Command;
import me.pvpb0t.callisto.api.friend.FriendManager;
import me.pvpb0t.callisto.api.friend.FriendObj;
import me.pvpb0t.callisto.api.util.Wrapper;
import me.pvpb0t.callisto.api.util.logger.Logger;

public class List extends Command implements Wrapper {

    public List()
    {
        super("List", new String[]{"list"}, "lists your friends");
    }

    @Override
    public void onTrigger(String arguments)
    {
        FriendManager friendManager = Callisto.getCallisto().getFriendManager();
        if(friendManager.getFriends().size() <= 0){
            Logger.getLogger().printToChat("You have no friends lmao");
        }

        for(FriendObj friendObj : Callisto.getCallisto().getFriendManager().getFriends()){
            FriendManager.getName(friendObj.getUuid()).thenAccept(s->{

                    Logger.getLogger().printToChat(s);

            });
            //Logger.getLogger().printToChat(friendManager.getName(friendObj.getUuid()));
        }

    }

}
