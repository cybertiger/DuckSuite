package org.cyberiantiger.minecraft.ducksuite.warps.listeners;

import org.cyberiantiger.minecraft.ducksuite.warps.managers.PermissionsManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class WarpsListener implements Listener {
    @EventHandler( priority = EventPriority.NORMAL )
    public void setPermissionGroup( final PlayerLoginEvent e ) {
        if ( e.getPlayer().hasPermission( "ducksuite.*" ) ) {
            PermissionsManager.addAllPermissions( e.getPlayer() );
        } else if ( e.getPlayer().hasPermission( "ducksuite.admin" ) ) {
            PermissionsManager.addAdminPermissions( e.getPlayer() );
        } else if ( e.getPlayer().hasPermission( "ducksuite.user" ) ) {
            PermissionsManager.addUserPermissions( e.getPlayer() );
        }
    }

}
