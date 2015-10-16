package org.cyberiantiger.minecraft.ducksuite.configs;

import org.cyberiantiger.minecraft.ducksuite.DuckSuite;
import net.cubespace.Yamler.Config.Config;

import java.io.File;

public class TeleportConfig extends Config {
    public TeleportConfig() {
        CONFIG_FILE = new File(DuckSuite.instance.getDataFolder(), "teleport.yml");
    }

    public Integer TeleportRequestExpireTime = 10;
}
