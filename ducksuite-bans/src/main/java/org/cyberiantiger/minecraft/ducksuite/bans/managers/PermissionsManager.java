package org.cyberiantiger.minecraft.ducksuite.bans.managers;

import org.cyberiantiger.minecraft.ducksuite.bans.DuckSuiteBans;
import org.bukkit.entity.Player;

public class PermissionsManager {
	
	public static void addAllPermissions(Player player) {
		player.addAttachment(DuckSuiteBans.instance, "ducksuite.bans.*", true);
	}
	public static void addAdminPermissions(Player player) {
		player.addAttachment(DuckSuiteBans.instance, "ducksuite.bans.admin", true);
	}
	public static void addModPermissions(Player player) {
		player.addAttachment(DuckSuiteBans.instance, "ducksuite.bans.mod", true);
	}
}
