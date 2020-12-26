package domain;

import java.util.Objects;

public class User {
    private final String name;

    private User(final String name) {
        this.name = name;
    }

    public static User from(final String username){
        return new User(username);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
