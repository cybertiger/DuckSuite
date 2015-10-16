package org.cyberiantiger.minecraft.ducksuite.homes.tasks;

import org.cyberiantiger.minecraft.ducksuite.homes.DuckSuiteHomes;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;

public class PluginMessageTask extends BukkitRunnable {

	private final ByteArrayOutputStream bytes;

	public PluginMessageTask(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}
	
	public void run() {
        Bukkit.getOnlinePlayers().iterator().next().sendPluginMessage(
                DuckSuiteHomes.instance,
                "DuckSuiteHomes",
                bytes.toByteArray());
	}

}
