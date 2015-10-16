package org.cyberiantiger.minecraft.ducksuite.tasks;

import java.util.ArrayList;
import java.util.Collection;
import org.cyberiantiger.minecraft.ducksuite.Utilities;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GlobalAnnouncements implements Runnable
{

    private ArrayList<String> list = new ArrayList<>();
    private int count = 0;

    public void addAnnouncement(String message)
    {
        list.add(Utilities.colorize(message));
    }

    public void run()
    {
        if (list.isEmpty()) {
            return;
        }

        Collection<ProxiedPlayer> players = ProxyServer.getInstance().getPlayers();
        if (players.isEmpty()) {
            return;
        }
        
        for (ProxiedPlayer player : players) {
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
