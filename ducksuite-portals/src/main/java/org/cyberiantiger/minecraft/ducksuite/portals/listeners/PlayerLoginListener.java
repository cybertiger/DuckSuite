package org.cyberiantiger.minecraft.ducksuite.portals.listeners;

import org.cyberiantiger.minecraft.ducksuite.portals.DuckSuitePortals;
import org.cyberiantiger.minecraft.ducksuite.portals.managers.PermissionsManager;
import org.cyberiantiger.minecraft.ducksuite.portals.managers.PortalsManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;


public class PlayerLoginListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerConnect( PlayerJoinEvent e ) {
        if ( !PortalsManager.RECEIVED ) {
            Bukkit.getScheduler().runTaskLaterAsynchronously( DuckSuitePortals.INSTANCE, new Runnable() {

                @Override
                public void run() {
                    if ( !PortalsManager.RECEIVED ) {
                        PortalsManager.RECEIVED = true;
                        PortalsManager.requestPortals();
                    }

                }
            }, 10L );
        }
        if ( PortalsManager.pendingTeleports.containsKey( e.getPlayer().getName() ) ) {
            Location l = PortalsManager.pendingTeleports.get( e.getPlayer().getName() );
            PortalsManager.pendingTeleports.remove( e.getPlayer().getName() );
            e.getPlayer().teleport( l );
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
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
