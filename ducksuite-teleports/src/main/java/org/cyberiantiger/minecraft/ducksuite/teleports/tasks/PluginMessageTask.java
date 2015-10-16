package org.cyberiantiger.minecraft.ducksuite.teleports.tasks;

import org.cyberiantiger.minecraft.ducksuite.teleports.DuckSuiteTeleports;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Collection;

import java.io.ByteArrayOutputStream;

public class PluginMessageTask extends BukkitRunnable {

    private final ByteArrayOutputStream bytes;

    public PluginMessageTask( ByteArrayOutputStream bytes ) {
        this.bytes = bytes;
    }

    public PluginMessageTask( ByteArrayOutputStream b, boolean empty ) {
        this.bytes = b;
    }

    @SuppressWarnings("unchecked")
    public void run() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if ( players.isEmpty() ) {
            return;
        }
        Player p = players.iterator().next();
        p.sendPluginMessage( DuckSuiteTeleports.instance, "DuckSuiteTeleports", bytes.toByteArray() );
    }

}
