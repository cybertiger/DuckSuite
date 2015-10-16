package org.cyberiantiger.minecraft.ducksuite.spawn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.cyberiantiger.minecraft.ducksuite.spawn.managers.SpawnManager;

public class SetWorldSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		SpawnManager.setWorldSpawn(sender);
		return true;

	}

}
