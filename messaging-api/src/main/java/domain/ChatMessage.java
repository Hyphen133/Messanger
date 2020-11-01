package domain;

import java.util.UUID;

class ChatMessage {
    private final UUID chatId;
    private String author;
    private final String content;

    private ChatMessage(final UUID chatId,final String author, final String content) {
        this.chatId = chatId;
        this.author = author;
        this.content = content;
    }

    public static ChatMessage from(UUID chatId, String author, String content) {
        return new ChatMessage(chatId, author, content);
    }

    public UUID getChatId() {
        return chatId;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor(){
        return author;
    }
}
