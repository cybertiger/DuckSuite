package org.cyberiantiger.minecraft.ducksuite;

import org.cyberiantiger.minecraft.ducksuite.commands.BanCommand;
import org.cyberiantiger.minecraft.ducksuite.commands.MOTDCommand;
import org.cyberiantiger.minecraft.ducksuite.commands.ReloadCommand;
import org.cyberiantiger.minecraft.ducksuite.commands.SeenCommand;
import org.cyberiantiger.minecraft.ducksuite.commands.UnbanCommand;
import org.cyberiantiger.minecraft.ducksuite.database.ConnectionHandler;
import org.cyberiantiger.minecraft.ducksuite.database.convert.Converter;
import org.cyberiantiger.minecraft.ducksuite.listeners.BansListener;
import org.cyberiantiger.minecraft.ducksuite.listeners.BansMessageListener;
import org.cyberiantiger.minecraft.ducksuite.listeners.HomesMessageListener;
import org.cyberiantiger.minecraft.ducksuite.listeners.PlayerListener;
import org.cyberiantiger.minecraft.ducksuite.listeners.PortalsMessageListener;
import org.cyberiantiger.minecraft.ducksuite.listeners.SpawnListener;
import org.cyberiantiger.minecraft.ducksuite.listeners.SpawnMessageListener;
import org.cyberiantiger.minecraft.ducksuite.listeners.TeleportsMessageListener;
import org.cyberiantiger.minecraft.ducksuite.listeners.WarpsMessageListener;
import org.cyberiantiger.minecraft.ducksuite.managers.ConfigManager;
import org.cyberiantiger.minecraft.ducksuite.managers.DatabaseManager;
import org.cyberiantiger.minecraft.ducksuite.managers.LoggingManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class DuckSuite extends Plugin
{

    public static DuckSuite instance;
    public static ProxyServer proxy;

    public void onEnable()
    {
        instance = this;
        LoggingManager.log(ChatColor.GREEN + "Starting DuckSuite");
        proxy = ProxyServer.getInstance();
        LoggingManager.log(ChatColor.GREEN + "Initialising Managers");

        ConnectionHandler connectionHandler = DatabaseManager.connectionPool.getConnection();
        connectionHandler.release();

        if (ConfigManager.main.ConvertFromBungeeSuite) {
            Converter converter = new Converter();
            converter.convert();
        }

        registerListeners();
        registerCommands();
    }

    private void registerCommands()
    {
        // A little hardcore. Prevent updating without a restart. But command squatting = bad!
        if (ConfigManager.main.MOTD_Enabled) {
            proxy.getPluginManager().registerCommand(this, new MOTDCommand());
        }
        if (ConfigManager.main.Seen_Enabled) {
            proxy.getPluginManager().registerCommand(this, new SeenCommand());
        }
        proxy.getPluginManager().registerCommand(this, new UnbanCommand());
        proxy.getPluginManager().registerCommand(this, new BanCommand());
        proxy.getPluginManager().registerCommand(this, new ReloadCommand());
    }

    private void registerListeners()
    {
        getProxy().registerChannel("DuckSuiteTeleports");       // Teleport out/in
        getProxy().registerChannel("DuckSuiteSpawns");         // Spawns out/in
        getProxy().registerChannel("DuckSuiteBans");           // Bans in
        getProxy().registerChannel("DuckSuitePortals");        // Portals out/in
        getProxy().registerChannel("DuckSuiteWarps");          // Warps in
        getProxy().registerChannel("DuckSuiteHomes");          // Homes in

        proxy.getPluginManager().registerListener(this, new PlayerListener());
        proxy.getPluginManager().registerListener(this, new BansMessageListener());
        proxy.getPluginManager().registerListener(this, new BansListener());
        proxy.getPluginManager().registerListener(this, new TeleportsMessageListener());
        proxy.getPluginManager().registerListener(this, new WarpsMessageListener());
        proxy.getPluginManager().registerListener(this, new HomesMessageListener());
        proxy.getPluginManager().registerListener(this, new PortalsMessageListener());
        proxy.getPluginManager().registerListener(this, new SpawnListener());
        proxy.getPluginManager().registerListener(this, new SpawnMessageListener());
    }

    public void onDisable()
    {
        DatabaseManager.connectionPool.closeConnections();
    }
}
