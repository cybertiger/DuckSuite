package org.cyberiantiger.minecraft.ducksuite.tasks;

import java.util.ArrayList;
import org.cyberiantiger.minecraft.ducksuite.Utilities;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ServerAnnouncements implements Runnable
{

    ArrayList<String> list = new ArrayList<String>();
    int count = 0;
    ServerInfo server;

    public ServerAnnouncements(ServerInfo server)
    {
        this.server = server;
    }

    public void addAnnouncement(String message)
    {
        list.add(Utilities.colorize(message));
    }

    public void run()
    {
        if (list.isEmpty()) {
            return;
        }
        if (server.getPlayers().isEmpty()) {
            return;
        }
        for (ProxiedPlayer player : server.getPlayers()) {
            for (String line : list.get(count).split("\n")) {
                // not sure if everything is thread safe. In doubt, leaving that one. It's colorized anyway.
                player.sendMessage(line);
            }
        }
        count++;
        if ((count + 1) > list.size()) {
            count = 0;
        }
    }
}
