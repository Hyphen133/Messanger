package ports;

import domain.Chat;
import domain.ChatMessage;
import java.util.List;
import java.util.UUID;

public interface ChatRepository {
    List<ChatMessage> getMessagesFor(Chat chat);
    void add(Chat chat);
    Chat getById(UUID id);
}
