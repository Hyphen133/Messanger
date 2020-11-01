package infrastructure;

import domain.Chat;
import domain.ChatRepository;
import application.Message;

import java.util.List;
import java.util.UUID;

public class InMemoryChatRepository implements ChatRepository {
    @Override
    public List<Message> getMessagesFor(Chat chat) {
        return null;
    }

    @Override
    public void add(Chat chat) {

    }

    @Override
    public void getById(UUID id) {

    }
}
