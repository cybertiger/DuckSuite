package org.cyberiantiger.minecraft.ducksuite.warps.managers;

import org.cyberiantiger.minecraft.ducksuite.warps.DuckSuiteWarps;
import org.cyberiantiger.minecraft.ducksuite.warps.tasks.PluginMessageTask;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class WarpsManager {
    public static void warpPlayer( CommandSender sender, String player, String warp ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "WarpPlayer" );
            out.writeUTF( sender.getName() );
            out.writeUTF( player );
            out.writeUTF( warp );
            out.writeBoolean( sender.hasPermission( "ducksuite.warps.warp." + warp.toLowerCase() ) || sender.hasPermission( "ducksuite.warps.warp.*" ) );
            out.writeBoolean( sender.hasPermission( "ducksuite.warps.bypass" ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteWarps.instance );
    }

    public static void setWarp( CommandSender sender, String name, boolean hidden, boolean global ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        Location l = ( ( Player ) sender ).getLocation();
        try {
            out.writeUTF( "SetWarp" );
            out.writeUTF( sender.getName() );
            out.writeUTF( name );
            out.writeUTF( l.getWorld().getName() );
            out.writeDouble( l.getX() );
            out.writeDouble( l.getY() );
            out.writeDouble( l.getZ() );
            out.writeFloat( l.getYaw() );
            out.writeFloat( l.getPitch() );
            out.writeBoolean( hidden );
            out.writeBoolean( global );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteWarps.instance );
    }

    public static void deleteWarp( CommandSender sender, String warp ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "DeleteWarp" );
            out.writeUTF( sender.getName() );
            out.writeUTF( warp );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteWarps.instance );
    }


    public static void listWarps( CommandSender sender ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "GetWarpsList" );
            out.writeUTF( sender.getName() );
            out.writeBoolean( sender.hasPermission( "ducksuite.warps.list.server" ) );
            out.writeBoolean( sender.hasPermission( "ducksuite.warps.list.global" ) );
            out.writeBoolean( sender.hasPermission( "ducksuite.warps.list.hidden" ) );
            out.writeBoolean( sender.hasPermission( "ducksuite.warps.bypass" ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteWarps.instance );
    }
}
