package org.cyberiantiger.minecraft.ducksuite.commands;

import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.cyberiantiger.minecraft.ducksuite.managers.AnnouncementManager;
import org.cyberiantiger.minecraft.ducksuite.managers.ConfigManager;
import org.cyberiantiger.minecraft.ducksuite.managers.PlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 * Command: /gsreload Permission needed: gesuit.reload or gesuit.admin Arguments: none What does it do: Reloads every config
 */
public class ReloadCommand extends Command
{

    public ReloadCommand()
    {
        super("dsreload");
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if (!(sender.hasPermission("ducksuite.reload") || sender.hasPermission("ducksuite.admin"))) {
            PlayerManager.sendMessageToTarget(sender, ConfigManager.messages.NO_PERMISSION);

            return;
        }

        try {
            ConfigManager.announcements.reload();
            ConfigManager.bans.reload();
            ConfigManager.main.reload();
            ConfigManager.spawn.reload();
            ConfigManager.messages.reload();
            AnnouncementManager.reloadAnnouncements();
            PlayerManager.sendMessageToTarget(sender, "All Configs reloaded");
        }
        catch (InvalidConfigurationException e) {
            e.printStackTrace();
            PlayerManager.sendMessageToTarget(sender, "Could not reload. Check the logs");
        }
    }
}
