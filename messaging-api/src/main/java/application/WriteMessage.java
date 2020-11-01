package application;

import java.util.UUID;

public class WriteMessage {

    private final UUID chatId;
    private final String author;
    private final String content;

    private WriteMessage(UUID chatId, final String author, String content) {

        this.chatId = chatId;
        this.author = author;
        this.content = content;
    }

    public static WriteMessage from(UUID chatId, String author, String content) {
        return new WriteMessage(chatId, author, content);
    }

    public UUID getChatId() {
        return chatId;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
