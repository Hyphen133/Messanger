package domain;

import java.util.UUID;

public class ChatMessage {
    private final UUID chatId;
    private final String content;

    private ChatMessage(final UUID chatId, final String content) {
        this.chatId = chatId;
        this.content = content;
    }

    public ChatMessage from(Message message){
        return new ChatMessage(message.getChatId(), message.getContent());
    }

    public static ChatMessage from(UUID chatId, String content) {
        return new ChatMessage(chatId,content);
    }

    public UUID getChatId() {
        return chatId;
    }

    public String getContent() {
        return content;
    }
}
