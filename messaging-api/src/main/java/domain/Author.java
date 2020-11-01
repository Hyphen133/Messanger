package domain;

public class Author {
    private final String nickname;

    private Author(String nickname) {
        this.nickname = nickname;
    }

    public static Author from(String nickname) {
        return new Author(nickname);
    }
}
