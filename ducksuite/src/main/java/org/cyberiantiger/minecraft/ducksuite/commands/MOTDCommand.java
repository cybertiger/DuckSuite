package org.cyberiantiger.minecraft.ducksuite.commands;

import org.cyberiantiger.minecraft.ducksuite.managers.ConfigManager;
import org.cyberiantiger.minecraft.ducksuite.managers.PlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 * Command: /motd
 * Permission needed: gesuit.motd or gesuit.admin
 * Arguments: none
 * What does it do: Prints out the MOTD
 */
public class MOTDCommand extends Command {
    public MOTDCommand() {
        super("motd");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender.hasPermission("ducksuite.motd") || sender.hasPermission("ducksuite.admin"))) {
            PlayerManager.sendMessageToTarget(sender, ConfigManager.messages.NO_PERMISSION);

            return;
        }
        PlayerManager.sendMessageToTarget(sender, ConfigManager.messages.MOTD);
    }
}


