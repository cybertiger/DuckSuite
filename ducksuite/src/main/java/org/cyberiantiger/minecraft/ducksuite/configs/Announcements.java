package org.cyberiantiger.minecraft.ducksuite.configs;

import org.cyberiantiger.minecraft.ducksuite.DuckSuite;
import org.cyberiantiger.minecraft.ducksuite.configs.SubConfig.AnnouncementEntry;
import net.cubespace.Yamler.Config.Config;

import java.io.File;
import java.util.HashMap;

public class Announcements extends Config {
    public Announcements() {
        CONFIG_FILE = new File(DuckSuite.instance.getDataFolder(), "announcements.yml");
    }

    public Boolean Enabled = true;
    public HashMap<String, AnnouncementEntry> Announcements = new HashMap<>();
}


