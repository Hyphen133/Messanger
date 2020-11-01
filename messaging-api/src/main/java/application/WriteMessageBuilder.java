package application;

import java.util.UUID;

public class WriteMessageBuilder {

    private String content;
    private UUID chatId;
    private String author;

    private WriteMessageBuilder() {
        this.content = "";
    }

    public static WriteMessageBuilder create(){
        return new WriteMessageBuilder();
    }

    public WriteMessageBuilder withContent(final String content){
        this.content = content;
        return this;
    }

    public WriteMessageBuilder forChat(final UUID chatId){
        this.chatId = chatId;
        return this;
    }

    public WriteMessageBuilder withAuthor(final String author){
        this.author = author;
        return this;
    }

    public WriteMessage build(){
        return WriteMessage.from(chatId, author, content);
    }
}
