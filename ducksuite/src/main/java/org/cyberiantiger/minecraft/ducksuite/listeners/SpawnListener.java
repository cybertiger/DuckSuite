package org.cyberiantiger.minecraft.ducksuite.listeners;

import org.cyberiantiger.minecraft.ducksuite.DuckSuite;
import org.cyberiantiger.minecraft.ducksuite.managers.ConfigManager;
import org.cyberiantiger.minecraft.ducksuite.managers.PlayerManager;
import org.cyberiantiger.minecraft.ducksuite.managers.SpawnManager;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.sql.SQLException;

public class SpawnListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void sendPlayerToHub(final PostLoginEvent e) throws SQLException {
        if (ConfigManager.spawn.ForceAllPlayersToProxySpawn && !SpawnManager.newPlayers.contains(e.getPlayer())) {
            if (SpawnManager.doesProxySpawnExist()) {
                SpawnManager.sendPlayerToProxySpawn(PlayerManager.getPlayer(e.getPlayer().getName()));
            } else {
                DuckSuite.instance.getLogger().warning("Wanted to use ForceAllPlayersToProxySpawn without a Proxy Spawn set");
            }
        }
    }
}
