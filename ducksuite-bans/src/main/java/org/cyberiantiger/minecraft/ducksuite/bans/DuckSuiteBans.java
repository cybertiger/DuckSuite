package org.cyberiantiger.minecraft.ducksuite.bans;

import org.cyberiantiger.minecraft.ducksuite.bans.commands.BanCommand;
import org.cyberiantiger.minecraft.ducksuite.bans.commands.BanHistoryCommand;
import org.cyberiantiger.minecraft.ducksuite.bans.commands.CheckBanCommand;
import org.cyberiantiger.minecraft.ducksuite.bans.commands.IPBanCommand;
import org.cyberiantiger.minecraft.ducksuite.bans.commands.KickAllCommand;
import org.cyberiantiger.minecraft.ducksuite.bans.commands.KickCommand;
import org.cyberiantiger.minecraft.ducksuite.bans.commands.ReloadBansCommand;
import org.cyberiantiger.minecraft.ducksuite.bans.commands.TempBanCommand;
import org.cyberiantiger.minecraft.ducksuite.bans.commands.UnBanIPCommand;
import org.cyberiantiger.minecraft.ducksuite.bans.commands.UnbanCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class DuckSuiteBans extends JavaPlugin {
    public static DuckSuiteBans instance;

    @Override
    public void onEnable() {
        instance = this;
        registerChannels();
        registerCommands();
    }

    private void registerCommands() {
        getCommand( "ban" ).setExecutor( new BanCommand() );
        getCommand( "checkban" ).setExecutor( new CheckBanCommand() );
        getCommand( "banhistory" ).setExecutor( new BanHistoryCommand() );
        getCommand( "ipban" ).setExecutor( new IPBanCommand() );
        getCommand( "kick" ).setExecutor( new KickCommand() );
        getCommand( "kickall" ).setExecutor( new KickAllCommand() );
        getCommand( "reloadbans" ).setExecutor( new ReloadBansCommand() );
        getCommand( "tempban" ).setExecutor( new TempBanCommand() );
        getCommand( "unban" ).setExecutor( new UnbanCommand() );
        getCommand( "unipban" ).setExecutor( new UnBanIPCommand() );
    }

    private void registerChannels() {
        this.getServer().getMessenger().registerOutgoingPluginChannel( this, "DuckSuiteBans" );
    }
}
