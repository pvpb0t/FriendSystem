package me.pvpb0t.callisto.impl.command;

import me.pvpb0t.callisto.Callisto;
import me.pvpb0t.callisto.api.command.Command;
import me.pvpb0t.callisto.api.friend.FriendManager;
import me.pvpb0t.callisto.api.util.Wrapper;
import me.pvpb0t.callisto.api.util.logger.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

public class Add extends Command implements Wrapper {
    AtomicBoolean shouldreturn = new AtomicBoolean(false);

    public Add()
    {
        super("Add", new String[]{"add"}, "adds friends");
    }

    @Override
    public void onTrigger(String arguments)
    {
        shouldreturn.set(false);

        if (arguments.equals(""))
        {
            printUsage();
            return;
        }

        FriendManager friendManager = Callisto.getCallisto().getFriendManager();

        if(arguments.length() > 22){
            //means its a uuid
            FriendManager.getName(arguments).thenAccept(s->{
                if(s.equals("invalid name")){
                    Logger.getLogger().printToChat("Player not found");
                    shouldreturn.set(true);
                }
            });
            //if(friendManager.getName(arguments).equals("invalid name")){
              //  Logger.getLogger().printToChat("Player not found");
                //return;
            //}
            if(!shouldreturn.get()){
                friendManager.addFriend(arguments, true);
                Logger.getLogger().printToChat("Added: " + friendManager.getName(arguments));
            }
        }else{
            //means its a playername
            FriendManager.getUuid(arguments).thenAccept(s->{
                if(s.equals("invalid name")){
                    Logger.getLogger().printToChat("Player not found");
                    shouldreturn.set(true);
                }
            });

            if(!shouldreturn.get()){
                friendManager.addFriend(arguments, false);
                Logger.getLogger().printToChat("Added: " + arguments);
            }

        }

    }

}
