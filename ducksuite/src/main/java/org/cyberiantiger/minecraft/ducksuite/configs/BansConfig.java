package org.cyberiantiger.minecraft.ducksuite.configs;

import org.cyberiantiger.minecraft.ducksuite.DuckSuite;
import net.cubespace.Yamler.Config.Config;

import java.io.File;

public class BansConfig extends Config {
    public BansConfig() {
        CONFIG_FILE = new File(DuckSuite.instance.getDataFolder(), "bans.yml");
    }

    public Boolean Enabled = true;
    public Boolean BroadcastBans = true;
    public Boolean BroadcastKicks = true;
    public Boolean DetectAltAccounts = true;
    public Boolean ShowAltAccountsOnlyIfBanned = true;
}
