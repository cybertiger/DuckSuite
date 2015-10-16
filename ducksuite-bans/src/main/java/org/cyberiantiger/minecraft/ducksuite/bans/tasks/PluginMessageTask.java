package org.cyberiantiger.minecraft.ducksuite.bans.tasks;

import java.io.ByteArrayOutputStream;
import org.cyberiantiger.minecraft.ducksuite.bans.DuckSuiteBans;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class PluginMessageTask extends BukkitRunnable
{

    private final ByteArrayOutputStream bytes;

    public PluginMessageTask(ByteArrayOutputStream bytes)
    {
        this.bytes = bytes;
    }

    public void run()
    {
        if (Bukkit.getOnlinePlayers().size() == 0) {
            DuckSuiteBans.instance.getLogger().info("Tried to send a pluginMessage with an empty server. Cancelling.");
        }
        else {
            Bukkit.getOnlinePlayers().iterator().next().sendPluginMessage(DuckSuiteBans.instance, "DuckSuiteBans", bytes.toByteArray());
        }
    }

}
