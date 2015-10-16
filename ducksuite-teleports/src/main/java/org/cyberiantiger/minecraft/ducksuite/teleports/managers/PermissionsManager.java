package org.cyberiantiger.minecraft.ducksuite.teleports.managers;

import org.cyberiantiger.minecraft.ducksuite.teleports.DuckSuiteTeleports;
import org.bukkit.entity.Player;

public class PermissionsManager {
	
	public static void addAllPermissions(Player player) {
		player.addAttachment(DuckSuiteTeleports.instance, "ducksuite.teleports.*", true);
	}
	public static void addAdminPermissions(Player player) {
		player.addAttachment(DuckSuiteTeleports.instance, "ducksuite.teleports.admin", true);
	}
	public static void addUserPermissions(Player player) {
		player.addAttachment(DuckSuiteTeleports.instance, "ducksuite.teleports.user", true);
	}
	public static void addVIPPermissions(Player player) {
		player.addAttachment(DuckSuiteTeleports.instance, "ducksuite.teleports.vip", true);
	}
}
