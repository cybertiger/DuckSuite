package org.cyberiantiger.minecraft.ducksuite.teleports.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.cyberiantiger.minecraft.ducksuite.teleports.managers.TeleportsManager;

public class ToggleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

			TeleportsManager.toggleTeleports(sender.getName());
			return true;
	}

}
