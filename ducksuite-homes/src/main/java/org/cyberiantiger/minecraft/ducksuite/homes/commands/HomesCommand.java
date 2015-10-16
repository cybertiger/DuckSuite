package org.cyberiantiger.minecraft.ducksuite.homes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.cyberiantiger.minecraft.ducksuite.homes.managers.HomesManager;

public class HomesCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		HomesManager.getHomesList(sender);
		return true;
	}

}
