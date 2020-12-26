package domain;

public class ChatMessage {
    private final Author author;
    private final String content;

    private ChatMessage(final Author author, final String content) {
        this.author = author;
        this.content = content;
    }

    public static ChatMessage from(final Author author, final String content) {
        return new ChatMessage(author, content);
    }

    public String getContent() {
        return content;
    }

    public Author getAuthor(){
        return author;
    }
}
