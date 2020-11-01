package application;

import java.util.UUID;

public class Message {

    private final UUID chatId;
    private final String author;
    private final String content;

    private Message(UUID chatId, final String author, String content) {

        this.chatId = chatId;
        this.author = author;
        this.content = content;
    }

    public static Message from(UUID chatId,String author, String content) {
        return new Message(chatId, author, content);
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
