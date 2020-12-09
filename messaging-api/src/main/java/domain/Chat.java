package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Chat {
    private final UUID id;
    private final List<ChatMessage> messages;
    private final Set<User> users;

    private Chat(final UUID id) {
        this.id = id;
        messages = new ArrayList<>();
        users = new HashSet<>();
    }

    public static Chat from(final UUID chatId){
        return new Chat(chatId);
    }

    public void addMessage(final ChatMessage message){
        messages.add(message);
    }

    public UUID getId() {
        return id;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void connectUser(final User user) {
        users.add(user);
    }

    public Set<User> getConnectedUsers() {
        return users;
    }
}
