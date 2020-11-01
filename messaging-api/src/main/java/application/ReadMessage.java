package application;

public class ReadMessage {

    private final String author;
    private final String content;

    public ReadMessage(final String author, final String content) {
        this.author = author;
        this.content = content;
    }

    public static ReadMessage from(final String author, final String content) {
        return new ReadMessage(author, content);
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
