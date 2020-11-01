package domain;

import application.WriteMessage;

import java.util.UUID;

public class NewMessageReceived {
    private final UUID chatId;
    private final String content;
    private final String author;

    public NewMessageReceived(final UUID chatId, final String author, final String content) {
        this.chatId = chatId;
        this.author = author;
        this.content = content;
    }

    public static NewMessageReceived from(UUID chatId, String author, String content) {
        return new NewMessageReceived(chatId, author, content);
    }

    public static NewMessageReceived from(WriteMessage writeMessage) {
        return NewMessageReceived.from(writeMessage.getChatId(), writeMessage.getAuthor(), writeMessage.getContent());
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
