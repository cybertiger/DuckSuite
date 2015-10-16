package org.cyberiantiger.minecraft.ducksuite.listeners;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.cyberiantiger.minecraft.ducksuite.FeatureDetector;
import org.cyberiantiger.minecraft.ducksuite.Utilities;
import org.cyberiantiger.minecraft.ducksuite.managers.BansManager;
import org.cyberiantiger.minecraft.ducksuite.managers.ConfigManager;
import org.cyberiantiger.minecraft.ducksuite.managers.DatabaseManager;
import org.cyberiantiger.minecraft.ducksuite.managers.LoggingManager;
import org.cyberiantiger.minecraft.ducksuite.objects.Ban;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BansListener implements Listener {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");

    @EventHandler
    public void banCheck(LoginEvent e) throws SQLException {
        if (DatabaseManager.bans.isPlayerBanned(e.getConnection().getName(), ((FeatureDetector.canUseUUID()) ? e.getConnection().getUUID() : null), e.getConnection().getAddress().getHostString())) {
            Ban b = DatabaseManager.bans.getBanInfo(e.getConnection().getName(), ((FeatureDetector.canUseUUID()) ? e.getConnection().getUUID() : null), e.getConnection().getAddress().getHostString());

            if (b == null) {
                return;
            }

            if (b.getType().equals("tempban")) {
                if (BansManager.checkTempBan(b)) {
                    e.setCancelled(true);

                    Date then = b.getBannedUntil();
                    Date now = new Date();
                    long timeDiff = then.getTime() - now.getTime();
                    long hours = timeDiff / (60 * 60 * 1000);
                    long mins = timeDiff / (60 * 1000) % 60;

                    e.setCancelReason(Utilities.colorize(ConfigManager.messages.TEMP_BAN_MESSAGE.replace("{sender}", b.getBannedBy()).replace("{time}", sdf.format(then) + " (" + hours + ":" + mins + " hours)").replace("{message}", b.getReason())));
                    LoggingManager.log(ChatColor.RED + e.getConnection().getName() + "'s connection refused due to being banned!");
                }
            } else {
                e.setCancelled(true);

                e.setCancelReason(Utilities.colorize(ConfigManager.messages.BAN_PLAYER_MESSAGE.replace("{sender}", b.getBannedBy()).replace("{message}", b.getReason())));
                LoggingManager.log(ChatColor.RED + e.getConnection().getName() + "'s connection refused due to being banned!");
            }
        }
    }
}
