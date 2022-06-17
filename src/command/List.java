package me.pvpb0t.callisto.impl.command;

import me.pvpb0t.callisto.Callisto;
import me.pvpb0t.callisto.api.command.Command;
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


        for(FriendObj friendObj : Callisto.getCallisto().getFriendManager().getFriends()){
            Logger.getLogger().printToChat(Callisto.getCallisto().getFriendManager().getName(friendObj.getUuid()));
        }

    }

}
