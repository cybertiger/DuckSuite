package org.cyberiantiger.minecraft.ducksuite.teleports.listeners;

import org.cyberiantiger.minecraft.ducksuite.teleports.DuckSuiteTeleports;
import org.cyberiantiger.minecraft.ducksuite.teleports.managers.TeleportsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class TeleportsMessageListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived( String channel, Player player, byte[] message ) {
        DataInputStream in = new DataInputStream( new ByteArrayInputStream( message ) );
        String task = null;
        try {
            task = in.readUTF();
            if ( task.equals( "TeleportToPlayer" ) ) {
                TeleportsManager.teleportPlayerToPlayer( in.readUTF(), in.readUTF() );
            }

            if ( task.equals( "TeleportToLocation" ) ) {
                TeleportsManager.teleportPlayerToLocation( in.readUTF(), in.readUTF(), in.readDouble(), in.readDouble(), in.readDouble(), in.readFloat(), in.readFloat() );
            }

        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if ( task.equals( "GetVersion" ) ) {
            String name = null;
            try {
                name = in.readUTF();
            } catch ( IOException e ) {

            }
            if ( name != null ) {
                Player p = Bukkit.getPlayer( name );
                p.sendMessage( ChatColor.RED + "Teleports - " + ChatColor.GOLD + DuckSuiteTeleports.instance.getDescription().getVersion() );
            }
            TeleportsManager.sendVersion();
            Bukkit.getConsoleSender().sendMessage( ChatColor.RED + "Teleports - " + ChatColor.GOLD + DuckSuiteTeleports.instance.getDescription().getVersion() );
        }
    }

}
