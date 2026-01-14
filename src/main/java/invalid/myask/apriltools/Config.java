package invalid.myask.apriltools;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {
    public static boolean use_vanilla_tabs = false;

    public static boolean usb_charger_enable = true;
    public static boolean tinted_glass_2_enable = true;
    public static boolean tinted_glass_2_original_recipe = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);


        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
