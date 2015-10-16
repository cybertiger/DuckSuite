package org.cyberiantiger.minecraft.ducksuite.pluginmessages;

import org.cyberiantiger.minecraft.ducksuite.DuckSuite;
import org.cyberiantiger.minecraft.ducksuite.objects.Spawn;
import org.cyberiantiger.minecraft.ducksuite.tasks.SendPluginMessage;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class SendSpawn {
    public static String OUTGOING_CHANNEL = "DuckSuiteSpawns";

    public static void execute(Spawn spawn) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);

        try {
            out.writeUTF("SendSpawn");
            out.writeUTF(spawn.getName());
            out.writeUTF(spawn.getLocation().getWorld());
            out.writeDouble(spawn.getLocation().getX());
            out.writeDouble(spawn.getLocation().getY());
            out.writeDouble(spawn.getLocation().getZ());
            out.writeFloat(spawn.getLocation().getYaw());
            out.writeFloat(spawn.getLocation().getPitch());
        } catch (IOException e) {
            e.printStackTrace();
        }

        DuckSuite.proxy.getScheduler().runAsync(DuckSuite.instance, new SendPluginMessage(OUTGOING_CHANNEL, spawn.getLocation().getServer(), bytes));
    }
}
