package application;

import domain.Chat;

import java.util.List;
import java.util.UUID;

public interface MessagingAPI {
    void write(WriteMessage writeMessage);

    Chat createChatFor(UUID id);

    List<ReadMessage> getMessagesForChat(UUID chatId);
}
