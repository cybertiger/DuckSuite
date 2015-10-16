package org.cyberiantiger.minecraft.ducksuite.portals.managers;

import org.cyberiantiger.minecraft.ducksuite.portals.DuckSuitePortals;
import org.bukkit.entity.Player;

public class PermissionsManager {

    public static void addAllPermissions( Player player ) {
        player.addAttachment( DuckSuitePortals.INSTANCE, "ducksuite.portals.*", true );
    }

    public static void addAdminPermissions( Player player ) {
        player.addAttachment( DuckSuitePortals.INSTANCE, "ducksuite.portals.admin", true );
    }

    public static void addUserPermissions( Player player ) {
        player.addAttachment( DuckSuitePortals.INSTANCE, "ducksuite.portals.user", true );
    }
}
