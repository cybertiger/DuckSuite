package org.cyberiantiger.minecraft.ducksuite.commands;

import org.cyberiantiger.minecraft.ducksuite.Utilities;
import org.cyberiantiger.minecraft.ducksuite.managers.BansManager;
import org.cyberiantiger.minecraft.ducksuite.managers.ConfigManager;
import org.cyberiantiger.minecraft.ducksuite.managers.PlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BanCommand extends Command {
    public BanCommand() {
        super("!ban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            return;
        }

        if (args.length == 0) {
            PlayerManager.sendMessageToTarget(sender, ConfigManager.messages.BUNGEE_COMMAND_BAN_USAGE);
            return;
        }

        if (Utilities.isIPAddress(args[0])) {
            BansManager.banIP(sender.getName(), args[0], null);
        } else {
            BansManager.banPlayer(sender.getName(), args[0], null);
        }
    }
}
