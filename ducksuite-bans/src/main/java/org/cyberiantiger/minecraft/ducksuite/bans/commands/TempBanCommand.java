package org.cyberiantiger.minecraft.ducksuite.bans.commands;

import java.util.Arrays;
import org.cyberiantiger.minecraft.ducksuite.bans.managers.BansManager;
import org.cyberiantiger.minecraft.ducksuite.bans.utils.StringUtils;
import org.cyberiantiger.minecraft.ducksuite.bans.utils.TimeParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class TempBanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args) {

        if(args.length > 1){
            String player = args[0];
            String timing = args[1];
            String reason = (args.length > 2) ? StringUtils.join(Arrays.copyOfRange(args, 2, args.length), " ") : "";
            int seconds = TimeParser.parseString(timing);
            if (seconds == 0) {
                return false;
            }
            BansManager.tempBanPlayer(sender.getName(), player, seconds, reason);
            return true;
        }

        return false;
    }

}
