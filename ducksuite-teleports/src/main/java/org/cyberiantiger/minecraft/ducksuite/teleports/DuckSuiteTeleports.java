package org.cyberiantiger.minecraft.ducksuite.teleports;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import org.cyberiantiger.minecraft.ducksuite.teleports.commands.BackCommand;
import org.cyberiantiger.minecraft.ducksuite.teleports.commands.TPACommand;
import org.cyberiantiger.minecraft.ducksuite.teleports.commands.TPAHereCommand;
import org.cyberiantiger.minecraft.ducksuite.teleports.commands.TPAcceptCommand;
import org.cyberiantiger.minecraft.ducksuite.teleports.commands.TPAllCommand;
import org.cyberiantiger.minecraft.ducksuite.teleports.commands.TPCommand;
import org.cyberiantiger.minecraft.ducksuite.teleports.commands.TPDenyCommand;
import org.cyberiantiger.minecraft.ducksuite.teleports.commands.TPHereCommand;
import org.cyberiantiger.minecraft.ducksuite.teleports.commands.ToggleCommand;
import org.cyberiantiger.minecraft.ducksuite.teleports.listeners.TeleportsListener;
import org.cyberiantiger.minecraft.ducksuite.teleports.listeners.TeleportsMessageListener;

public class DuckSuiteTeleports extends JavaPlugin {
    public static DuckSuiteTeleports instance;

    @Override
    public void onEnable() {
        instance = this;
        registerListeners();
        registerChannels();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("tp").setExecutor(new TPCommand());
        getCommand("tphere").setExecutor(new TPHereCommand());
        getCommand("tpall").setExecutor(new TPAllCommand());
        getCommand("tpa").setExecutor(new TPACommand());
        getCommand("tpahere").setExecutor(new TPAHereCommand());
        getCommand("tpaccept").setExecutor(new TPAcceptCommand());
        getCommand("tpdeny").setExecutor(new TPDenyCommand());
        getCommand("back").setExecutor(new BackCommand());
        getCommand("tptoggle").setExecutor(new ToggleCommand());
    }

    private void registerChannels() {
        Bukkit.getMessenger().registerIncomingPluginChannel(this,
                "DuckSuiteTeleport", new TeleportsMessageListener());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this,
                "DuckSuiteTeleport");
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(
                new TeleportsListener(), this);
    }


}
