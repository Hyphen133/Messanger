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

    private Chat(final UUID id, List<ChatMessage> messages) {
        this.id = id;
        this.messages = messages;
        this.users = new HashSet<>();
    }

    public static Chat from(final UUID chatId){
        return new Chat(chatId, new ArrayList<>());
    }

    public static Chat from(final UUID chatId, List<ChatMessage> messages){
        return new Chat(chatId, messages);
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

    public void disconnectUser(final User user) {
        users.remove(user);
    }

    public Set<User> getConnectedUsers() {
        return users;
    }
}
