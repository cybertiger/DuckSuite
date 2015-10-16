package org.cyberiantiger.minecraft.ducksuite.managers;

import org.cyberiantiger.minecraft.ducksuite.FeatureDetector;
import org.cyberiantiger.minecraft.ducksuite.objects.GSPlayer;
import org.cyberiantiger.minecraft.ducksuite.objects.Home;
import org.cyberiantiger.minecraft.ducksuite.objects.Location;
import org.cyberiantiger.minecraft.ducksuite.pluginmessages.TeleportToLocation;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomesManager {
    public static void createNewHome(GSPlayer player, int serverLimit, int globalLimit, String home, Location loc) {
        if (getHome(player, home) == null) {
            int globalHomeCount = getPlayersGlobalHomeCount(player);
            int serverHomeCount = getPlayersServerHomeCount(player);

            if (globalHomeCount >= globalLimit) {
                PlayerManager.sendMessageToTarget(player, ConfigManager.messages.NO_HOMES_ALLOWED_GLOBAL);
                return;
            }

            if (serverHomeCount >= serverLimit) {
                PlayerManager.sendMessageToTarget(player, ConfigManager.messages.NO_HOMES_ALLOWED_SERVER);
                return;
            }

            if (player.getHomes().get(player.getServer()) == null) {
                player.getHomes().put(player.getServer(), new ArrayList<Home>());
            }

            Home homeObject = new Home(player, home, loc);
            player.getHomes().get(player.getServer()).add(homeObject);
            DatabaseManager.homes.addHome(homeObject);

            PlayerManager.sendMessageToTarget(player, ConfigManager.messages.HOME_SET);
        } else {
            Home home1 = getHome(player, home);
            home1.setLoc(loc);
            DatabaseManager.homes.updateHome(home1);

            PlayerManager.sendMessageToTarget(player, ConfigManager.messages.HOME_UPDATED);
        }
    }

    private static int getPlayersGlobalHomeCount(GSPlayer player) {
        int count = 0;

        for (ArrayList<Home> list : player.getHomes().values()) {
            count += list.size();
        }

        return count;
    }

    private static int getPlayersServerHomeCount(GSPlayer player) {
        ArrayList<Home> list = player.getHomes().get(player.getServer());

        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public static void listPlayersHomes(GSPlayer player) {
        if (player.getHomes().isEmpty()) {
            PlayerManager.sendMessageToTarget(player, ConfigManager.messages.NO_HOMES);
            return;
        }

        boolean empty = true;
        for (String server : player.getHomes().keySet()) {
            String homes;

            if (server.equals(player.getServer())) {
                homes = ChatColor.RED + server + ": " + ChatColor.BLUE;
            } else {
                homes = ChatColor.GOLD + server + ": " + ChatColor.BLUE;
            }

            for (Home h : player.getHomes().get(server)) {
                homes += h.name + ", ";
                empty = false;
            }

            if (empty) {
                PlayerManager.sendMessageToTarget(player, ConfigManager.messages.NO_HOMES);
                return;
            }

            PlayerManager.sendMessageToTarget(player, homes.substring(0, homes.length() - 2));
        }

    }

    public static void loadPlayersHomes(GSPlayer player) {
        List<Home> homes = DatabaseManager.homes.getHomesForPlayer((FeatureDetector.canUseUUID()) ? player.getUuid() : player.getName());

        for(Home home : homes) {
            if (player.getHomes().get(home.loc.getServer().getName()) == null) {
                ArrayList<Home> list = new ArrayList<>();
                list.add(home);
                player.getHomes().put(home.loc.getServer().getName(), list);
            } else {
                player.getHomes().get(home.loc.getServer().getName()).add(home);
            }
        }
    }


    public static Home getHome(GSPlayer player, String home) {
        HashMap<String, Home> found = new HashMap<>();

        for (ArrayList<Home> list : player.getHomes().values()) {
            for (Home h : list) {
                if (h.name.toLowerCase().equals(home.toLowerCase())) {
                    found.put(h.loc.getServer().getName(), h);
                }
            }
        }

        if (found.size() == 0) {
            return null;
        } else {
            if (found.containsKey(player.getServer())) {
                return found.get(player.getServer());
            }

            return found.values().iterator().next();
        }
    }

    public static void sendPlayerToHome(GSPlayer player, String home) {
        Home h = getHome(player, home);
        if (h == null) {
            PlayerManager.sendMessageToTarget(player, ConfigManager.messages.HOME_DOES_NOT_EXIST);
            return;
        }

        TeleportToLocation.execute(player, h.loc);

        PlayerManager.sendMessageToTarget(player, ConfigManager.messages.SENT_HOME);
    }

    public static void deleteHome(String player, String home) {
        GSPlayer p = PlayerManager.getPlayer(player);
        Home h = getHome(p, home);

        if (h == null) {
            PlayerManager.sendMessageToTarget(p, ConfigManager.messages.HOME_DOES_NOT_EXIST);
            return;
        }

        for (ArrayList<Home> list : p.getHomes().values()) {
            if (list.contains(h)) {
                list.remove(h);
                break;
            }
        }

        DatabaseManager.homes.deleteHome(h);

        PlayerManager.sendMessageToTarget(p, ConfigManager.messages.HOME_DELETED);
    }
}

