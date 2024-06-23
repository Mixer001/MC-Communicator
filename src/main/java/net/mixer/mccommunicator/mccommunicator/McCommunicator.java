package net.mixer.mccommunicator.mccommunicator;

import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class McCommunicator implements ModInitializer {
    public static final String MOD_ID = "mccommunicator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("MC Communicator started!");
        ServerMessageEvents.CHAT_MESSAGE.register(this::onChatMessage);
    }

    private void onChatMessage(SignedMessage signedMessage, ServerPlayerEntity serverPlayerEntity, MessageType.Parameters parameters) {
        LOGGER.info("Message sent!");
    }
}
