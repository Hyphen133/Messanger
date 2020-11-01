package domain;

public class Author {
    private final String nickname;

    private Author(final String nickname) {
        this.nickname = nickname;
    }

    public static Author from(final String nickname) {
        return new Author(nickname);
    }

    public String getNickname() {
        return nickname;
    }
}
