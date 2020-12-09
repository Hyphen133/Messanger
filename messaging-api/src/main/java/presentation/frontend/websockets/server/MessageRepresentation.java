package presentation.frontend.websockets.server;


public class MessageRepresentation {
    public String chatId;
    public String author;
    public String content;

    @Override
    public String toString() {
        return "MessageRepresentation{" +
                "chatId='" + chatId + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
