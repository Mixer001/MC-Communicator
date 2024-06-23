package net.mixer.mccommunicator.mccommunicator;

import net.mixer.mccommunicator.mccommunicator.config.ModConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;

public class McCommunicator implements ModInitializer {
    public static final String MOD_ID = "mccommunicator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final ChatHandler chatHandler = new ChatHandler();

    @Override
    public void onInitialize() {
        LOGGER.info("MC Communicator loading!");

        ModConfigs.registerConfigs();

        if (ModConfigs.WEBHOOK_URL.isEmpty()) {
            LOGGER.warn("No webhook URL found in config. MC Communicator will not be active. Edit the config and restart the server.");
        }
        else {
            LOGGER.info("Webhook URL found in config, messages will be sent to the given address.");
            ServerMessageEvents.CHAT_MESSAGE.register(chatHandler::onChatMessage);
        }
    }
}
