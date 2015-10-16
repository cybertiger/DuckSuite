package org.cyberiantiger.minecraft.ducksuite.portals.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.cyberiantiger.minecraft.ducksuite.portals.managers.PortalsManager;


public class ListPortalsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		PortalsManager.getPortalsList(sender.getName());
		return false;
	}

}
