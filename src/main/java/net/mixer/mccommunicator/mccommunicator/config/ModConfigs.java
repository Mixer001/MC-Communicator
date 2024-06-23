package net.mixer.mccommunicator.mccommunicator.config;

import com.mojang.datafixers.util.Pair;
import net.mixer.mccommunicator.mccommunicator.McCommunicator;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static String WEBHOOK_URL;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(McCommunicator.MOD_ID).provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("webhook.URL", ""), "The URL of your discord webhook");
    }

    private static void assignConfigs() {
        WEBHOOK_URL = CONFIG.getOrDefault("webhook.URL", "");

        McCommunicator.LOGGER.info("Config has been set properly.");
    }
}
