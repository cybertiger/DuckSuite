package org.cyberiantiger.minecraft.ducksuite.warps.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.cyberiantiger.minecraft.ducksuite.warps.managers.WarpsManager;

public class DeleteWarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if(args.length>0){
			WarpsManager.deleteWarp(sender,args[0]);
			return true;
		}
		return false;

	}

}
