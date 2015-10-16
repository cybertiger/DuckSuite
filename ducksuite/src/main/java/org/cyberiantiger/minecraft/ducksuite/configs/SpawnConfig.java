package org.cyberiantiger.minecraft.ducksuite.configs;

import org.cyberiantiger.minecraft.ducksuite.DuckSuite;
import net.cubespace.Yamler.Config.Config;

import java.io.File;

public class SpawnConfig extends Config {
    public SpawnConfig() {
        CONFIG_FILE = new File(DuckSuite.instance.getDataFolder(), "spawns.yml");
    }

	public Boolean SpawnNewPlayerAtNewspawn = false;
	public Boolean ForceAllPlayersToProxySpawn = false;
}
