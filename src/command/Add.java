package me.pvpb0t.callisto.api.src.command;

package me.pvpb0t.callisto.impl.command;

import me.pvpb0t.callisto.Callisto;
import me.pvpb0t.callisto.api.command.Command;
import me.pvpb0t.callisto.api.friend.FriendManager;
import me.pvpb0t.callisto.api.util.Wrapper;
import me.pvpb0t.callisto.api.util.logger.Logger;

public class Add extends Command implements Wrapper {

    public Add()
    {
        super("Add", new String[]{"add"}, "adds friends");
    }

    @Override
    public void onTrigger(String arguments)
    {

        if (arguments.equals(""))
        {
            printUsage();
            return;
        }

        FriendManager friendManager = Callisto.getCallisto().getFriendManager();

        if(arguments.length() > 22){
            //means its a uuid
            if(friendManager.getName(arguments) == "invalid name"){
                Logger.getLogger().printToChat("Player not found");
                return;
            }
            Callisto.getCallisto().getFriendManager().addFriend(arguments, true);
            Logger.getLogger().printToChat("Added: " + Callisto.getCallisto().getFriendManager().getName(arguments));
        }else{
            //means its a playername
            if(friendManager.getUuid(arguments) == "invalid name"){
                Logger.getLogger().printToChat("Player not found");
                return;
            }
            Callisto.getCallisto().getFriendManager().addFriend(arguments, false);
            Logger.getLogger().printToChat("Added: " + arguments);
        }

    }

}
