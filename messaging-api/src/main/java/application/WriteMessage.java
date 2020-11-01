package application;

import java.util.UUID;

public class WriteMessage {

    private final UUID chatId;
    private final String author;
    private final String content;

    private WriteMessage(final UUID chatId, final String author, final String content) {

        this.chatId = chatId;
        this.author = author;
        this.content = content;
    }

    public static WriteMessage from(final UUID chatId, final String author, final String content) {
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
