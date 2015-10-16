package org.cyberiantiger.minecraft.ducksuite.spawn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.cyberiantiger.minecraft.ducksuite.spawn.managers.SpawnManager;

public class SetServerSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		SpawnManager.setServerSpawn(sender);
		return true;
	}

}
