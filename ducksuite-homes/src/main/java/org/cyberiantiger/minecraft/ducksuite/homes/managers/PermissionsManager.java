package org.cyberiantiger.minecraft.ducksuite.homes.managers;

import org.bukkit.entity.Player;

import org.cyberiantiger.minecraft.ducksuite.homes.DuckSuiteHomes;

public class PermissionsManager {

    public static void addAllPermissions( Player player ) {
        player.addAttachment( DuckSuiteHomes.instance, "ducksuite.homes.*", true );
    }

    public static void addAdminPermissions( Player player ) {
        player.addAttachment( DuckSuiteHomes.instance, "ducksuite.homes.admin", true );
    }

    public static void addUserPermissions( Player player ) {
        player.addAttachment( DuckSuiteHomes.instance, "ducksuite.homes.user", true );
    }
}
