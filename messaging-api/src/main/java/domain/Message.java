package domain;

public class Message {

    private Message(String content) {

    }

    public static Message from(String content) {
        return new Message(content);
    }
}
