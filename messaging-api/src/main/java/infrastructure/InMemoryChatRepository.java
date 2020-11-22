package infrastructure;

import domain.Chat;
import domain.ChatMessage;
import ports.ChatRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryChatRepository implements ChatRepository {
    private Map<UUID, Chat> chats;

    public InMemoryChatRepository() {
        chats = new HashMap<>();
    }

    @Override
    public List<ChatMessage> getMessagesFor(final Chat chat) {
        return chat.getMessages();
    }

    @Override
    public void add(final Chat chat) {
        if (!this.chats.containsKey(chat.getId())){
            this.chats.put(chat.getId(), chat);
        }
    }

    @Override
    public Chat getById(final UUID id) {
        if(!this.chats.containsKey(id)){
            throw new RuntimeException("No chat with such id " + id);
        }
        return this.chats.get(id);
    }
}
