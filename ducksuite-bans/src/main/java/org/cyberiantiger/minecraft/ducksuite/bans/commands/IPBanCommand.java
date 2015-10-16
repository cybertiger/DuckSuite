package org.cyberiantiger.minecraft.ducksuite.bans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.cyberiantiger.minecraft.ducksuite.bans.managers.BansManager;


public class IPBanCommand implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if(args.length==1){
			BansManager.ipBanPlayer(sender.getName(),args[0], "");
			return true;
		}
		
		if(args.length>1){
			String msg = "";
			for(String data: args){
				if(!data.equals(args[0])){
					msg+=data+" ";
				}
			}
			BansManager.ipBanPlayer(sender.getName(),args[0],msg);
			return true;
		}

		return false;
	}

}
