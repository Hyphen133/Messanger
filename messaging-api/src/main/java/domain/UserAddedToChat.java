package domain;

import java.util.UUID;

public class UserAddedToChat {
    private final String username;
    private final UUID chatId;

    private UserAddedToChat(final String username, final UUID chatId) {
        this.username = username;
        this.chatId = chatId;
    }

    public static UserAddedToChat from(final String username, final UUID chatId){
        return new UserAddedToChat(username, chatId);
    }

    public String getUsername() {
        return username;
    }

    public UUID getChatId() {
        return chatId;
    }
}
