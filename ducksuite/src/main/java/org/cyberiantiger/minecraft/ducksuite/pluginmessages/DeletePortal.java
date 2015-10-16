package org.cyberiantiger.minecraft.ducksuite.pluginmessages;

import org.cyberiantiger.minecraft.ducksuite.DuckSuite;
import org.cyberiantiger.minecraft.ducksuite.objects.Portal;
import org.cyberiantiger.minecraft.ducksuite.tasks.SendPluginMessage;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class DeletePortal {
    public static String OUTGOING_CHANNEL = "DuckSuitePortals";

    public static void execute(Portal p) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);

        try {
            out.writeUTF("DeletePortal");
            out.writeUTF(p.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        DuckSuite.proxy.getScheduler().runAsync(DuckSuite.instance, new SendPluginMessage(OUTGOING_CHANNEL, p.getServer(), bytes));
    }
}
