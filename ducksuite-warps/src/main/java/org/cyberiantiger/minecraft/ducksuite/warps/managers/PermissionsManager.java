package org.cyberiantiger.minecraft.ducksuite.warps.managers;

import org.cyberiantiger.minecraft.ducksuite.warps.DuckSuiteWarps;
import org.bukkit.entity.Player;

public class PermissionsManager {

    public static void addAllPermissions( Player player ) {
        player.addAttachment( DuckSuiteWarps.instance, "ducksuite.warps.*", true );
    }

    public static void addAdminPermissions( Player player ) {
        player.addAttachment( DuckSuiteWarps.instance, "ducksuite.warps.admin", true );
    }

    public static void addUserPermissions( Player player ) {
        player.addAttachment( DuckSuiteWarps.instance, "ducksuite.warps.user", true );
    }
}
