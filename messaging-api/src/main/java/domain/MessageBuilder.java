package domain;

import java.util.UUID;

public class MessageBuilder {

    private String content;
    private UUID chatId;

    private MessageBuilder() {
        this.content = "";
    }

    public static MessageBuilder create(){
        return new MessageBuilder();
    }

    public MessageBuilder withContent(String content){
        this.content = content;
        return this;
    }

    public MessageBuilder forChat(UUID chatId){
        this.chatId = chatId;
        return this;
    }


    public Message build(){
        return Message.from(chatId, content);
    }
}
