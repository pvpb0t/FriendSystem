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
            if(friendManager.getName(arguments) == "invalid name"){
                Logger.getLogger().printToChat("Player not found");
                return;
            }
            boolean isfriended = Callisto.getCallisto().getFriendManager().isFriend(arguments);
            if(!isfriended){
                Logger.getLogger().printToChat(Callisto.getCallisto().getFriendManager().getName(arguments) + " is not your friend, LMAO");
                return;
            }

            // final FriendObj friend = new FriendObj(arguments);
            FriendObj friendObj = friendManager.getFriend(arguments);

            if(friendObj != null){
                Callisto.getCallisto().getFriendManager().getFriends().remove(friendObj);
            }

            Logger.getLogger().printToChat("Removed: " + Callisto.getCallisto().getFriendManager().getName(arguments));
        } else {
            //means its a playername
            if(friendManager.getUuid(arguments) == "invalid name"){
                Logger.getLogger().printToChat("Player not found");
                return;
            }
            boolean isfriended = Callisto.getCallisto().getFriendManager().isFriend(friendManager.getUuid(arguments));
            if(!isfriended){
                Logger.getLogger().printToChat(arguments+ " is not your friend, LMAO");
                return;
            }

            //final FriendObj friend = new FriendObj(Callisto.getCallisto().getFriendManager().getUuid(arguments));
            FriendObj friendObj = friendManager.getFriend(friendManager.getUuid(arguments));
            if(friendObj != null){
                Callisto.getCallisto().getFriendManager().getFriends().remove(friendObj);
            }


            Logger.getLogger().printToChat("Removed: " + arguments);
        }

    }
}