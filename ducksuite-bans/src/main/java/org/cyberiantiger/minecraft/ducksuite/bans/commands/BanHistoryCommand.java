package org.cyberiantiger.minecraft.ducksuite.bans.commands;

import org.cyberiantiger.minecraft.ducksuite.bans.managers.BansManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanHistoryCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {

        if (args.length > 0) {
            BansManager.displayPlayerBanHistory(sender.getName(), args[0]);
            return true;
        }

        return false;
    }

}
