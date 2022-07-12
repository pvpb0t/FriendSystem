package me.pvpb0t.callisto.impl.command;

import me.pvpb0t.callisto.Callisto;
import me.pvpb0t.callisto.api.command.Command;
import me.pvpb0t.callisto.api.friend.FriendManager;
import me.pvpb0t.callisto.api.friend.FriendObj;
import me.pvpb0t.callisto.api.util.Wrapper;
import me.pvpb0t.callisto.api.util.logger.Logger;

public class Del extends Command implements Wrapper {

    public Del() {
        super("Del", new String[]{"del"}, "removes friends");
    }

    @Override
    public void onTrigger(String arguments) {
        FriendManager friendManager = Callisto.getCallisto().getFriendManager();
        if (arguments.equals("")) {
            printUsage();
            return;
        }

        if (arguments.length() > 22) {
            //means its a uuid
            FriendManager.getName(arguments).thenAccept(s->{
                if(s.equals("invalid name")){
                    Logger.getLogger().printToChat("Player not found");
                }
            });
            boolean isfriended = friendManager.isFriend(arguments);
            if(!isfriended){
                Logger.getLogger().printToChat(friendManager.getName(arguments) + " is not your friend, LMAO");
                return;
            }

            FriendObj friendObj = friendManager.getFriend(arguments);

            if(friendObj != null){
                friendManager.getFriends().remove(friendObj);
            }

            Logger.getLogger().printToChat("Removed: " + friendManager.getName(arguments));
        } else {
            //means its a playername
            FriendManager.getUuid(arguments).thenAccept(s-> {

                if (s.equals("invalid name")) {
                    Logger.getLogger().printToChat("Player not found");
                    return;
                }
                boolean isfriended = friendManager.isFriend(s);
                if (!isfriended) {
                    Logger.getLogger().printToChat(arguments + " is not your friend, LMAO");
                    return;
                }

                FriendObj friendObj = friendManager.getFriend(s);
                if (friendObj != null) {
                    friendManager.getFriends().remove(friendObj);
                }


                Logger.getLogger().printToChat("Removed: " + arguments);
            });
        }

    }
}
