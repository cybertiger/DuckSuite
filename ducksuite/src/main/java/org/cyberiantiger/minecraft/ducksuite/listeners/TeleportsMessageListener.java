package org.cyberiantiger.minecraft.ducksuite.listeners;

import org.cyberiantiger.minecraft.ducksuite.managers.LoggingManager;
import org.cyberiantiger.minecraft.ducksuite.managers.PlayerManager;
import org.cyberiantiger.minecraft.ducksuite.managers.TeleportManager;
import org.cyberiantiger.minecraft.ducksuite.objects.GSPlayer;
import org.cyberiantiger.minecraft.ducksuite.objects.Location;
import org.cyberiantiger.minecraft.ducksuite.pluginmessages.TeleportToLocation;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class TeleportsMessageListener implements Listener {

    @EventHandler
    public void receivePluginMessage(PluginMessageEvent event) throws IOException, SQLException {
        if (event.isCancelled()) {
            return;
        }
        if (!(event.getSender() instanceof Server))
            return;
        if (!event.getTag().equalsIgnoreCase("DuckSuiteTeleports")) {
            return;
        }

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
        String task = in.readUTF();

        if (task.equals("TpAccept")) {
            TeleportManager.acceptTeleportRequest(PlayerManager.getPlayer(in.readUTF()));
            return;
        }

        if (task.equals("TeleportToLocation")) {
            GSPlayer player = PlayerManager.getPlayer(in.readUTF());
            String server = in.readUTF();
            TeleportToLocation.execute(player, new Location((!server.equals("")) ? server : ((Server) event.getSender()).getInfo().getName(), in.readUTF(), in.readDouble(), in.readDouble(), in.readDouble()));
            return;
        }

        if (task.equals("PlayersTeleportBackLocation")) {
            TeleportManager.setPlayersTeleportBackLocation(PlayerManager.getPlayer(in.readUTF()), new Location(((Server) event.getSender()).getInfo(), in.readUTF(), in.readDouble(), in.readDouble(), in.readDouble()));
            return;
        }

        if (task.equals("PlayersDeathBackLocation")) {
            TeleportManager.setPlayersDeathBackLocation(PlayerManager.getPlayer(in.readUTF()), new Location(((Server) event.getSender()).getInfo(), in.readUTF(), in.readDouble(), in.readDouble(), in.readDouble()));
            return;
        }

        if (task.equals("TeleportToPlayer")) {
            TeleportManager.teleportPlayerToPlayer(in.readUTF(), in.readUTF(), in.readUTF(), in.readBoolean(), in.readBoolean());
            return;
        }

        if (task.equals("TpaHereRequest")) {
            TeleportManager.requestPlayerTeleportToYou(in.readUTF(), in.readUTF());
            return;
        }

        if (task.equals("TpaRequest")) {
            TeleportManager.requestToTeleportToPlayer(in.readUTF(), in.readUTF());
            return;
        }

        if (task.equals("TpDeny")) {
            TeleportManager.denyTeleportRequest(PlayerManager.getPlayer(in.readUTF()));
            return;
        }

        if (task.equals("TpAll")) {
            TeleportManager.tpAll(in.readUTF(), in.readUTF());
            return;
        }

        if (task.equals("SendPlayerBack")) {
            TeleportManager.sendPlayerToLastBack(PlayerManager.getPlayer(in.readUTF()), in.readBoolean(), in.readBoolean());
            return;
        }

        if (task.equals("ToggleTeleports")) {
            TeleportManager.togglePlayersTeleports(PlayerManager.getPlayer(in.readUTF()));
            return;
        }

        if (task.equals("SendVersion")) {
            LoggingManager.log(in.readUTF());
        }
    }

}
