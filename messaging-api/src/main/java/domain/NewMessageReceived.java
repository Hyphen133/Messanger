package domain;

import java.util.UUID;

public class NewMessageReceived {
    private final UUID chatId;
    private final String content;

    public NewMessageReceived(final UUID chatId, final String content) {
        this.chatId = chatId;
        this.content = content;
    }

    public static NewMessageReceived from(UUID chatId, String content) {
        return new NewMessageReceived(chatId,content);
    }

    public static NewMessageReceived from(Message message) {
        return NewMessageReceived.from(message.getChatId(), message.getContent());
    }

    public UUID getChatId() {
        return chatId;
    }

    public String getContent() {
        return content;
    }
}
