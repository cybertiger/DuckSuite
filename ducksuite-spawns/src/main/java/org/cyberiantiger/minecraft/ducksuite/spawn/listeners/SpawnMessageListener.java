package org.cyberiantiger.minecraft.ducksuite.spawn.listeners;

import org.cyberiantiger.minecraft.ducksuite.spawn.DuckSuiteSpawns;
import org.cyberiantiger.minecraft.ducksuite.spawn.managers.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class SpawnMessageListener implements PluginMessageListener, Listener {

    @Override
    public void onPluginMessageReceived( String channel, Player player, byte[] message ) {
        DataInputStream in = new DataInputStream( new ByteArrayInputStream( message ) );
        String task;

        try {
            task = in.readUTF();
            switch ( task ) {
                case "SendSpawn":
                    SpawnManager.addSpawn( in.readUTF(), in.readUTF(), in.readDouble(), in.readDouble(), in.readDouble(), in.readFloat(), in.readFloat() );
                    break;
                case "GetVersion":
                    String name = null;
                    try {
                        name = in.readUTF();
                    } catch ( IOException e ) {
                    }
                    if ( name != null ) {
                        Player p = Bukkit.getPlayer( name );
                        p.sendMessage( ChatColor.RED + "Spawns - " + ChatColor.GOLD + DuckSuiteSpawns.INSTANCE.getDescription().getVersion() );
                    }
                    Bukkit.getConsoleSender().sendMessage( ChatColor.RED + "Spawns - " + ChatColor.GOLD + DuckSuiteSpawns.INSTANCE.getDescription().getVersion() );
                    SpawnManager.sendVersion();
                    break;
            }

        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }


}
