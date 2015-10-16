package org.cyberiantiger.minecraft.ducksuite.warps.tasks;

import org.cyberiantiger.minecraft.ducksuite.warps.DuckSuiteWarps;
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
					DuckSuiteWarps.instance,
					"DuckSuiteWarps",
					bytes.toByteArray());
	
	}

}
