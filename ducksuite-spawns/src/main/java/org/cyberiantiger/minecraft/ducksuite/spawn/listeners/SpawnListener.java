package org.cyberiantiger.minecraft.ducksuite.spawn.listeners;

import org.cyberiantiger.minecraft.ducksuite.spawn.DuckSuiteSpawns;
import org.cyberiantiger.minecraft.ducksuite.spawn.managers.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnListener implements Listener {

    @EventHandler( priority = EventPriority.LOWEST )
    public void playerLogin( PlayerJoinEvent e ) {
        if ( !SpawnManager.HAS_SPAWNS ) {
            Bukkit.getScheduler().runTaskLaterAsynchronously( DuckSuiteSpawns.INSTANCE, new Runnable() {
                @Override
                public void run() {
                    if ( !SpawnManager.HAS_SPAWNS ) {
                        SpawnManager.getSpawns();
                        SpawnManager.HAS_SPAWNS = true;
                    }
                }
            }, 10L );
        }

        Player p = e.getPlayer();
        if ( !p.hasPlayedBefore() ) {
            if ( SpawnManager.hasWorldSpawn( p.getWorld() ) && p.hasPermission( "ducksuite.spawns.new.world" ) ) {
                SpawnManager.sendPlayerToWorldSpawn( p );
            } else if ( SpawnManager.hasServerSpawn() && p.hasPermission( "ducksuite.spawns.new.server" ) ) {
                SpawnManager.sendPlayerToServerSpawn( p );
            } else if ( p.hasPermission( "ducksuite.spawns.new.global" ) ) {
                SpawnManager.sendPlayerToProxySpawn( p, true );
            }
        }

    }

    @EventHandler( priority = EventPriority.LOWEST )
    public void playerRespawn( PlayerRespawnEvent e ) {
        Player p = e.getPlayer();
        if ( p.getBedSpawnLocation() != null && p.hasPermission( "ducksuite.spawns.spawn.bed" ) ) {
            e.setRespawnLocation( p.getBedSpawnLocation() );
        } else if ( SpawnManager.hasWorldSpawn( p.getWorld() ) && p.hasPermission( "ducksuite.spawns.spawn.world" ) ) {
            e.setRespawnLocation( SpawnManager.getWorldSpawn( p.getWorld() ) );
        } else if ( SpawnManager.hasServerSpawn() && p.hasPermission( "ducksuite.spawns.spawn.server" ) ) {
            e.setRespawnLocation( SpawnManager.getServerSpawn() );
        } else if ( p.hasPermission( "ducksuite.spawns.spawn.global" ) ) {
            if ( SpawnManager.hasWorldSpawn( p.getWorld() ) ) {
                e.setRespawnLocation( SpawnManager.getWorldSpawn( p.getWorld() ) );
            } else if ( SpawnManager.hasServerSpawn() ) {
                e.setRespawnLocation( SpawnManager.getServerSpawn() );
            }

            SpawnManager.sendPlayerToProxySpawn( p, true );
        }
    }

}
