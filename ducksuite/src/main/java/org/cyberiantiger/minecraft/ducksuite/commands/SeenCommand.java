package org.cyberiantiger.minecraft.ducksuite.commands;

import org.cyberiantiger.minecraft.ducksuite.managers.ConfigManager;
import org.cyberiantiger.minecraft.ducksuite.managers.PlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 * Command: /seen
 * Permission needed: gesuit.seen or gesuit.admin
 * Arguments: none
 * What does it do: Displays &lt;player&gt; last online time
 */
public class SeenCommand extends Command {
    public SeenCommand() {
        super("seen");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender.hasPermission("ducksuite.seen") || sender.hasPermission("ducksuite.admin"))) {
            PlayerManager.sendMessageToTarget(sender, ConfigManager.messages.NO_PERMISSION);

            return;
        }

        if (args.length == 0) {
            PlayerManager.sendMessageToTarget(sender, ConfigManager.messages.BUNGEE_COMMAND_SEEN_USAGE);
            return;
        }

        PlayerManager.sendMessageToTarget(sender, PlayerManager.getLastSeeninfos(args[0]));
    }
}
