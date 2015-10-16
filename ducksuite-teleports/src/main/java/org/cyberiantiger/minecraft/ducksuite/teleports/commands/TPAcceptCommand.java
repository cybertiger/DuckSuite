package org.cyberiantiger.minecraft.ducksuite.teleports.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.cyberiantiger.minecraft.ducksuite.teleports.managers.TeleportsManager;


public class TPAcceptCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

			TeleportsManager.tpAccept(sender);
			return true;
	}

}
