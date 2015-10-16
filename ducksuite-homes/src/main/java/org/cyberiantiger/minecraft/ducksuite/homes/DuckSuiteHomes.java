package org.cyberiantiger.minecraft.ducksuite.homes;

import org.cyberiantiger.minecraft.ducksuite.homes.commands.DelHomeCommand;
import org.cyberiantiger.minecraft.ducksuite.homes.commands.HomeCommand;
import org.cyberiantiger.minecraft.ducksuite.homes.commands.HomesCommand;
import org.cyberiantiger.minecraft.ducksuite.homes.commands.ImportHomesCommand;
import org.cyberiantiger.minecraft.ducksuite.homes.commands.SetHomeCommand;
import org.cyberiantiger.minecraft.ducksuite.homes.listeners.HomesListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DuckSuiteHomes extends JavaPlugin {
	public static DuckSuiteHomes instance;

	@Override
	public void onEnable() {
		instance = this;
		registerListeners();
		registerChannels();
		registerCommands();
	}
	
	private void registerCommands() {
		getCommand("sethome").setExecutor(new SetHomeCommand());
		getCommand("home").setExecutor(new HomeCommand());
		getCommand("delhome").setExecutor(new DelHomeCommand());
		getCommand("homes").setExecutor(new HomesCommand());
		getCommand("importhomes").setExecutor(new ImportHomesCommand());
	}

	private void registerChannels() {
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "DuckSuiteHomes");
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(
				new HomesListener(), this);
	}


}
