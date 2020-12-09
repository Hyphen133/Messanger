package application;

import domain.Chat;
import domain.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MessagingAPI {
    void write(WriteMessage writeMessage);

    Chat createChatFor(UUID chatId);

    List<ReadMessage> getMessagesForChat(UUID chatId);

    void connectUserToChat(String username, UUID chatId);

    Set<User> getUsersConnectedToChat(UUID chatId);
}
