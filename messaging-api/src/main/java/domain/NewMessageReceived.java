package domain;

import application.WriteMessage;

import java.util.UUID;

public class NewMessageReceived {
    private final UUID chatId;
    private final ChatMessage chatMessage;

    public NewMessageReceived(final UUID chatId, final ChatMessage chatMessage) {
        this.chatId = chatId;
        this.chatMessage = chatMessage;
    }

    public static NewMessageReceived from(final UUID chatId, final ChatMessage chatMessage) {
        return new NewMessageReceived(chatId,chatMessage);
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public UUID getChatId() {
        return chatId;
    }
}
