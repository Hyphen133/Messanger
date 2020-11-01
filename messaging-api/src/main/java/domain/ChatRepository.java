package domain;

import application.Message;

import java.util.List;
import java.util.UUID;

public interface ChatRepository {
    List<Message> getMessagesFor(Chat chat);
    void add(Chat chat);
    void getById(UUID id);
}
