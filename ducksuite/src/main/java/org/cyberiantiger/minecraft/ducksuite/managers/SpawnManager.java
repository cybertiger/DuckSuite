package org.cyberiantiger.minecraft.ducksuite.managers;

import java.util.ArrayList;
import java.util.List;
import org.cyberiantiger.minecraft.ducksuite.objects.GSPlayer;
import org.cyberiantiger.minecraft.ducksuite.objects.Location;
import org.cyberiantiger.minecraft.ducksuite.objects.Spawn;
import org.cyberiantiger.minecraft.ducksuite.pluginmessages.SendSpawn;
import org.cyberiantiger.minecraft.ducksuite.pluginmessages.TeleportToLocation;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;

public class SpawnManager {
    public static Location NewPlayerSpawn;
    public static Location ProxySpawn;

    public static ArrayList<ProxiedPlayer> newPlayers = new ArrayList<>();

    public static void loadSpawns() {
        ProxySpawn = DatabaseManager.spawns.getSpawn("ProxySpawn");
        NewPlayerSpawn = DatabaseManager.spawns.getSpawn("NewPlayerSpawn");
    }

    public static boolean doesProxySpawnExist() {
        return ProxySpawn != null;
    }

    public static boolean doesNewPlayerSpawnExist() {
        return NewPlayerSpawn != null;
    }

    public static void sendPlayerToProxySpawn(GSPlayer player) {
        if (!doesProxySpawnExist()) {
            PlayerManager.sendMessageToTarget(player, ConfigManager.messages.SPAWN_DOES_NOT_EXIST);
            return;
        }

        TeleportToLocation.execute(player, ProxySpawn);
    }


    public static void sendPlayerToNewPlayerSpawn(GSPlayer player) {
        if (!doesNewPlayerSpawnExist()) {
            PlayerManager.sendMessageToTarget(player, ConfigManager.messages.SPAWN_DOES_NOT_EXIST);
            return;
        }

        TeleportToLocation.execute(player, NewPlayerSpawn);
    }


    public static void sendSpawns(Server s) {
        List<Spawn> spawnList = DatabaseManager.spawns.getSpawnsForServer(s.getInfo().getName());

        for (Spawn spawn : spawnList) {
            SendSpawn.execute(spawn);
        }
    }

    private static void setSpawn(GSPlayer player, Spawn spawn, boolean exists) {
        if (exists) {
            DatabaseManager.spawns.updateSpawn(spawn);
            PlayerManager.sendMessageToTarget(player, ConfigManager.messages.SPAWN_UPDATED);
        } else {
            DatabaseManager.spawns.insertSpawn(spawn);
            PlayerManager.sendMessageToTarget(player, ConfigManager.messages.SPAWN_SET);
        }

        SendSpawn.execute(spawn);
    }

    public static void setServerSpawn(GSPlayer p, Location l, boolean exists) {
        Spawn spawn = new Spawn("server", l);

        setSpawn(p, spawn, exists);
    }

    public static void setWorldSpawn(GSPlayer p, Location l, boolean exists) {
        Spawn spawn = new Spawn(l.getWorld(), l);

        setSpawn(p, spawn, exists);
    }

    public static void setNewPlayerSpawn(GSPlayer p, Location l) {
        Spawn spawn = new Spawn("NewPlayerSpawn", l);

        if (NewPlayerSpawn != null) {
            DatabaseManager.spawns.updateSpawn(spawn);
            p.sendMessage(ConfigManager.messages.SPAWN_UPDATED);
        } else {
            DatabaseManager.spawns.insertSpawn(spawn);
            p.sendMessage(ConfigManager.messages.SPAWN_SET);
        }

        NewPlayerSpawn = l;
    }

    public static void setProxySpawn(GSPlayer p, Location l) {
        Spawn spawn = new Spawn("ProxySpawn", l);

        if (ProxySpawn != null) {
            DatabaseManager.spawns.updateSpawn(spawn);
            p.sendMessage(ConfigManager.messages.SPAWN_UPDATED);
        } else {
            DatabaseManager.spawns.insertSpawn(spawn);
            p.sendMessage(ConfigManager.messages.SPAWN_SET);
        }

        ProxySpawn = l;
    }
}
