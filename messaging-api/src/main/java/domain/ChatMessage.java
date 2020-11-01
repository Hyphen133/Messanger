package domain;

public class ChatMessage {
    private Author author;
    private final String content;

    private ChatMessage(final Author author, final String content) {
        this.author = author;
        this.content = content;
    }

    public static ChatMessage from(Author author, String content) {
        return new ChatMessage(author, content);
    }

    public String getContent() {
        return content;
    }

    public Author getAuthor(){
        return author;
    }
}
