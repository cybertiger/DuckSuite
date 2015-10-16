package org.cyberiantiger.minecraft.ducksuite.spawn.tasks;

import org.cyberiantiger.minecraft.ducksuite.spawn.DuckSuiteSpawns;
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
					DuckSuiteSpawns.INSTANCE,
					"DuckSuiteSpawns",
					bytes.toByteArray());
	
	}

}
