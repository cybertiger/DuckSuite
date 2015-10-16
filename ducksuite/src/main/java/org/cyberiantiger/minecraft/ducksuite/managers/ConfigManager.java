package org.cyberiantiger.minecraft.ducksuite.managers;

import org.cyberiantiger.minecraft.ducksuite.configs.Announcements;
import org.cyberiantiger.minecraft.ducksuite.configs.BansConfig;
import org.cyberiantiger.minecraft.ducksuite.configs.MainConfig;
import org.cyberiantiger.minecraft.ducksuite.configs.Messages;
import org.cyberiantiger.minecraft.ducksuite.configs.SpawnConfig;
import org.cyberiantiger.minecraft.ducksuite.configs.TeleportConfig;
import net.cubespace.Yamler.Config.InvalidConfigurationException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ConfigManager {
    public static Announcements announcements = new Announcements();
    public static BansConfig bans = new BansConfig();
    public static MainConfig main = new MainConfig();
    public static SpawnConfig spawn = new SpawnConfig();
    public static TeleportConfig teleport = new TeleportConfig();
    public static Messages messages = new Messages();

    static {
        try {
            messages.init();
            announcements.init();
            bans.init();
            main.init();
            spawn.init();
            teleport.init();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
