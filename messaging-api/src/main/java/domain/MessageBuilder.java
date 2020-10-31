package domain;

public class MessageBuilder {

    private String content;

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

    public Message build(){
        return Message.from(content);
    }
}
