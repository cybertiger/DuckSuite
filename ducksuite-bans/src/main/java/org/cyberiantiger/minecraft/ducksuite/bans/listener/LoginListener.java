package org.cyberiantiger.minecraft.ducksuite.bans.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import org.cyberiantiger.minecraft.ducksuite.bans.managers.PermissionsManager;

public class LoginListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void setFormatChat(final PlayerLoginEvent e) {
		if(e.getPlayer().hasPermission("ducksuite.*")){
			PermissionsManager.addAllPermissions(e.getPlayer());
		}else if(e.getPlayer().hasPermission("ducksuite.admin")){
			PermissionsManager.addAdminPermissions(e.getPlayer());
		}else if(e.getPlayer().hasPermission("ducksuite.mod")){
			PermissionsManager.addModPermissions(e.getPlayer());
		}
	}

	

}
