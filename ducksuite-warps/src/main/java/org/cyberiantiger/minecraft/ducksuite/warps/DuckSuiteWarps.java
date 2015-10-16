package org.cyberiantiger.minecraft.ducksuite.warps;

import org.cyberiantiger.minecraft.ducksuite.warps.commands.DeleteWarpCommand;
import org.cyberiantiger.minecraft.ducksuite.warps.commands.ListWarpsCommand;
import org.cyberiantiger.minecraft.ducksuite.warps.commands.SetWarpCommand;
import org.cyberiantiger.minecraft.ducksuite.warps.commands.WarpCommand;
import org.cyberiantiger.minecraft.ducksuite.warps.listeners.WarpsListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DuckSuiteWarps extends JavaPlugin {
	public static DuckSuiteWarps instance;

	@Override
	public void onEnable() {
		instance=this;
		registerListeners();
		registerChannels();
		registerCommands();
	}

	private void registerCommands() {
		getCommand("warp").setExecutor(new WarpCommand());
		getCommand("warps").setExecutor(new ListWarpsCommand());
		getCommand("setwarp").setExecutor(new SetWarpCommand());
		getCommand("delwarp").setExecutor(new DeleteWarpCommand());
	}

	private void registerChannels() {
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "DuckSuiteWarps");
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(
				new WarpsListener(), this);
	}

}
