package org.cyberiantiger.minecraft.ducksuite.teleports.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.cyberiantiger.minecraft.ducksuite.teleports.managers.TeleportsManager;


public class TPDenyCommand implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		TeleportsManager.tpDeny(sender.getName());
			return true;
	}

}
