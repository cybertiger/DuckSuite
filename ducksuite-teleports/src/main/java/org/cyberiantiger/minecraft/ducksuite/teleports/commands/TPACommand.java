package org.cyberiantiger.minecraft.ducksuite.teleports.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.cyberiantiger.minecraft.ducksuite.teleports.managers.TeleportsManager;


public class TPACommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(args.length>0){
			TeleportsManager.tpaRequest(sender,args[0]);
			return true;
		}
		return false;
	}

}
