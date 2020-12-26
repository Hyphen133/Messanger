package ports;

import domain.Chat;
import domain.ChatMessage;
import domain.User;
import java.util.List;
import java.util.UUID;

public interface ChatRepository {
    List<ChatMessage> getMessagesFor(UUID chat);
    void add(Chat chat);
    Chat getById(UUID id);
    void save(final UUID chatId, ChatMessage chatMessage);
    void addUserToChat(User user, UUID chatId);
}
