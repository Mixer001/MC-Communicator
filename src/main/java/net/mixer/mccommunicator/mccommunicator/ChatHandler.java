package net.mixer.mccommunicator.mccommunicator;

import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.mixer.mccommunicator.mccommunicator.config.ModConfigs;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatHandler {
    private final HttpClient client;

    public ChatHandler() {
        client = HttpClient.newHttpClient();
    }

    public void onChatMessage(SignedMessage signedMessage, ServerPlayerEntity serverPlayerEntity, MessageType.Parameters parameters) {
        String messageJson = String.format("{\"username\":\"%s\", \"avatar_url\":\"%s\", \"content\":\"%s\"}",
                serverPlayerEntity.getName().getString(),
                "https://mc-heads.net/avatar/" + serverPlayerEntity.getUuidAsString() + ".png",
                signedMessage.getContent().getString()
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ModConfigs.WEBHOOK_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(messageJson))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 204) {
                McCommunicator.LOGGER.warn("Possible error. Response code {}", response.statusCode());
            }
        }
        catch (IOException | InterruptedException exception) {
            McCommunicator.LOGGER.error(exception.getMessage());
        }
    }
}
