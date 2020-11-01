package domain;

import java.util.UUID;

public class Message {

    private final UUID chatId;
    private final String content;

    private Message(UUID chatId, String content) {

        this.chatId = chatId;
        this.content = content;
    }

    public static Message from(UUID chatId, String content) {
        return new Message(chatId,content);
    }

    public UUID getChatId() {
        return chatId;
    }

    public String getContent() {
        return content;
    }
}
