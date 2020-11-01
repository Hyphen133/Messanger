package domain;

import java.util.UUID;

public class ChatMessage {
    private final UUID chatId;
    private Author author;
    private final String content;

    private ChatMessage(final UUID chatId,final Author author, final String content) {
        this.chatId = chatId;
        this.author = author;
        this.content = content;
    }

    public static ChatMessage from(UUID chatId, Author author, String content) {
        return new ChatMessage(chatId, author, content);
    }

    public UUID getChatId() {
        return chatId;
    }

    public String getContent() {
        return content;
    }

    public Author getAuthor(){
        return author;
    }
}
