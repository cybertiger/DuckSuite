package org.cyberiantiger.minecraft.ducksuite.pluginmessages;

import org.cyberiantiger.minecraft.ducksuite.DuckSuite;
import org.cyberiantiger.minecraft.ducksuite.objects.GSPlayer;
import org.cyberiantiger.minecraft.ducksuite.tasks.SendPluginMessage;
import net.md_5.bungee.api.ProxyServer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class TeleportToPlayer {
    public static String OUTGOING_CHANNEL = "DuckSuiteTeleport";

    public static void execute(GSPlayer player, GSPlayer target) {
        if (!player.getServer().equals(target.getServer())) {
            player.getProxiedPlayer().connect(ProxyServer.getInstance().getServerInfo(target.getServer()));
        }

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);

        try {
            out.writeUTF("TeleportToPlayer");
            out.writeUTF(player.getName());
            out.writeUTF(target.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        DuckSuite.proxy.getScheduler().runAsync(DuckSuite.instance, new SendPluginMessage(OUTGOING_CHANNEL, ProxyServer.getInstance().getServerInfo(target.getServer()), bytes));
    }
}
