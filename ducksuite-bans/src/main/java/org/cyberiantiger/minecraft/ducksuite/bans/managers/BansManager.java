package org.cyberiantiger.minecraft.ducksuite.bans.managers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.cyberiantiger.minecraft.ducksuite.bans.DuckSuiteBans;
import org.cyberiantiger.minecraft.ducksuite.bans.tasks.PluginMessageTask;
import org.cyberiantiger.minecraft.ducksuite.bans.utils.TimeParser;
import org.bukkit.ChatColor;


public class BansManager {

    public static void banPlayer( String sender, String player, String msg ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "BanPlayer" );
            out.writeUTF( sender );
            out.writeUTF( player );
            out.writeUTF( msg );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );
    }

    public static void kickAll( String sender, String msg ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "KickAll" );
            out.writeUTF( sender );
            out.writeUTF( msg );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );

    }

    public static void kickPlayer( String sender, String player, String msg ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "KickPlayer" );
            out.writeUTF( sender );
            out.writeUTF( player );
            out.writeUTF( msg );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );

    }

    public static void tempBanPlayer( String sender, String player, int seconds, String reason ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "TempBanPlayer" );
            out.writeUTF( sender );
            out.writeUTF( player );
            out.writeInt( seconds );
            out.writeUTF( reason );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );
    }

    /**
     *
     * @deprecated Does not validate timing is non-zero or a valid time.
     */
    @Deprecated
    public static void tempBanPlayer( String sender, String player, String timing, String reason ) {
        int seconds = TimeParser.parseString(timing);
        tempBanPlayer( sender, player, seconds, reason );
    }


    public static void unbanPlayer( String sender, String player ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "UnbanPlayer" );
            out.writeUTF( sender );
            out.writeUTF( player );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );

    }

    public static void reloadBans( String string ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "ReloadBans" );
            out.writeUTF( string );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );
    }

    public static void ipBanPlayer( String sender, String player, String msg ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "IPBanPlayer" );
            out.writeUTF( sender );
            out.writeUTF( player );
            out.writeUTF( msg );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );

    }

    public static void unipBanPlayer( String sender, String player, String msg ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "UnIPBanPlayer" );
            out.writeUTF( sender );
            out.writeUTF( player );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );

    }

    public static void checkPlayerBans( String sender, String player ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "CheckPlayerBans" );
            out.writeUTF( sender );
            out.writeUTF( player );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );
    }
    
    public static void displayPlayerBanHistory( String sender, String player ) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "DisplayPlayerBanHistory" );
            out.writeUTF( sender );
            out.writeUTF( player );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );
    }


    public static void sendVersion() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream( b );
        try {
            out.writeUTF( "SendVersion" );
            out.writeUTF( ChatColor.RED + "Bans - " + ChatColor.GOLD + DuckSuiteBans.instance.getDescription().getVersion() );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        new PluginMessageTask( b ).runTaskAsynchronously( DuckSuiteBans.instance );
    }
}
