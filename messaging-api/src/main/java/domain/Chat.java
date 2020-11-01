package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Chat {
    private final UUID id;
    private final List<ChatMessage> messages;

    private Chat(final UUID id) {
        this.id = id;
        messages = new ArrayList<>();
    }

    public static Chat from(UUID id){
        return new Chat(id);
    }

    public void addMessage(ChatMessage message){
        messages.add(message);
    }

    public UUID getId() {
        return id;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }
}
