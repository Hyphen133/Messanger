package infrastructure;

import domain.Chat;
import domain.ChatRepository;
import application.Message;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InMemoryChatRepository implements ChatRepository {
    HashMap<UUID, Chat> chats;

    public InMemoryChatRepository() {
        chats = new HashMap<>();
    }

    @Override
    public List<Message> getMessagesFor(Chat chat) {
        return null;
    }

    @Override
    public void add(Chat chat) {
        if (!this.chats.containsKey(chat.getId())){
            this.chats.put(chat.getId(), chat);
        }
    }

    @Override
    public Chat getById(UUID id) {
        if(!this.chats.containsKey(id)){
            throw new RuntimeException("No chat with such id " + id);
        }
        return this.chats.get(id);
    }
}
