package application;

import domain.Chat;

import java.util.UUID;

public interface MessagingAPI {
    void write(Message message);

    Chat createChatFor(UUID id);
}
