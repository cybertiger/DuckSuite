package org.cyberiantiger.minecraft.ducksuite.portals;


import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.cyberiantiger.minecraft.ducksuite.portals.commands.DeletePortalCommand;
import org.cyberiantiger.minecraft.ducksuite.portals.commands.ListPortalsCommand;
import org.cyberiantiger.minecraft.ducksuite.portals.commands.SetPortalCommand;
import org.cyberiantiger.minecraft.ducksuite.portals.listeners.AntiBurnListener;
import org.cyberiantiger.minecraft.ducksuite.portals.listeners.PhysicsListener;
import org.cyberiantiger.minecraft.ducksuite.portals.listeners.PlayerLoginListener;
import org.cyberiantiger.minecraft.ducksuite.portals.listeners.PlayerMoveListener;
import org.cyberiantiger.minecraft.ducksuite.portals.listeners.PortalsMessageListener;
import org.cyberiantiger.minecraft.ducksuite.portals.managers.PortalsManager;
import org.cyberiantiger.minecraft.ducksuite.portals.objects.Portal;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class DuckSuitePortals extends JavaPlugin {
    public static DuckSuitePortals INSTANCE = null;
    public static WorldEditPlugin WORLDEDIT = null;

    @Override
    public void onEnable() {
        INSTANCE = this;
        loadWorldEdit();
        registerListeners();
        registerChannels();
        registerCommands();
    }

    @Override
    public void onDisable() {
        for ( ArrayList<Portal> list : PortalsManager.PORTALS.values() ) {
            for ( Portal p : list ) {
                p.clearPortal();
            }
        }
    }


    private void loadWorldEdit() {
        WORLDEDIT = ( WorldEditPlugin ) getServer().getPluginManager().getPlugin( "WorldEdit" );
    }

    private void registerCommands() {
        getCommand( "setportal" ).setExecutor( new SetPortalCommand() );
        getCommand( "delportal" ).setExecutor( new DeletePortalCommand() );
        getCommand( "portals" ).setExecutor( new ListPortalsCommand() );
    }

    private void registerChannels() {
        Bukkit.getMessenger().registerIncomingPluginChannel( this, "DuckSuitePortals", new PortalsMessageListener() );
        Bukkit.getMessenger().registerOutgoingPluginChannel( this, "DuckSuitePortals" );
        Bukkit.getMessenger().registerOutgoingPluginChannel( this, "BungeeCord" );
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents( new PlayerMoveListener(), this );
        getServer().getPluginManager().registerEvents( new PhysicsListener(), this );
        getServer().getPluginManager().registerEvents( new PlayerLoginListener(), this );
        getServer().getPluginManager().registerEvents( new AntiBurnListener(), this );
    }
}
