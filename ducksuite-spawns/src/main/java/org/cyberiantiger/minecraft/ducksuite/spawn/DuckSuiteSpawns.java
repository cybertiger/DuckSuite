package org.cyberiantiger.minecraft.ducksuite.spawn;

import org.cyberiantiger.minecraft.ducksuite.spawn.commands.GlobalSpawnCommand;
import org.cyberiantiger.minecraft.ducksuite.spawn.commands.ServerSpawnCommand;
import org.cyberiantiger.minecraft.ducksuite.spawn.commands.SetGlobalSpawnCommand;
import org.cyberiantiger.minecraft.ducksuite.spawn.commands.SetNewSpawnCommand;
import org.cyberiantiger.minecraft.ducksuite.spawn.commands.SetServerSpawnCommand;
import org.cyberiantiger.minecraft.ducksuite.spawn.commands.SetWorldSpawnCommand;
import org.cyberiantiger.minecraft.ducksuite.spawn.commands.SpawnCommand;
import org.cyberiantiger.minecraft.ducksuite.spawn.commands.WorldSpawnCommand;
import org.cyberiantiger.minecraft.ducksuite.spawn.listeners.SpawnListener;
import org.cyberiantiger.minecraft.ducksuite.spawn.listeners.SpawnMessageListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class DuckSuiteSpawns extends JavaPlugin {
	public static Plugin INSTANCE = null;

	@Override
	public void onEnable() {
		INSTANCE = this;
		registerListeners();
		registerChannels();
		registerCommands();
	}

	private void registerCommands() {
		getCommand("setnewspawn").setExecutor(new SetNewSpawnCommand());
		getCommand("setworldspawn").setExecutor(new SetWorldSpawnCommand());
		getCommand("setserverspawn").setExecutor(new SetServerSpawnCommand());
		getCommand("setglobalspawn").setExecutor(new SetGlobalSpawnCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("worldspawn").setExecutor(new WorldSpawnCommand());
		getCommand("serverspawn").setExecutor(new ServerSpawnCommand());
		getCommand("globalspawn").setExecutor(new GlobalSpawnCommand());
	}

	private void registerChannels() {
		Bukkit.getMessenger().registerIncomingPluginChannel(this,
				"DuckSuiteSpawns", new SpawnMessageListener());
		Bukkit.getMessenger().registerOutgoingPluginChannel(this,
				"DuckSuiteSpawns");
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(
				new SpawnListener(), this);
	}

}
