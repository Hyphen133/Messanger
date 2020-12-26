package infrastructure.persistance.local;

import domain.Chat;
import domain.ChatMessage;
import domain.User;
import ports.ChatRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryChatRepository implements ChatRepository {
    private final Map<UUID, Chat> chats;

    public InMemoryChatRepository() {
        chats = new HashMap<>();
    }

    @Override
    public List<ChatMessage> getMessagesFor(final UUID chatId) {
        return chats.get(chatId).getMessages();
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

    @Override
    public void save(final UUID chatId, final ChatMessage chatMessage) {
        chats.get(chatId).addMessage(chatMessage);
    }

    @Override
    public void addUserToChat(final User user, final UUID chatId) {
        chats.get(chatId).connectUser(user);
    }
}
