package infrastructure;

import domain.Author;
import domain.Chat;
import domain.ChatMessage;
import domain.User;
import domain.UserAddedToChat;
import ports.MessageEventDispatcher;
import domain.NewMessageReceived;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalMessageEventDispatcher implements MessageEventDispatcher {
    private Map<UUID, Chat> chats;

    public LocalMessageEventDispatcher() {
        chats = new HashMap<>();
    }

    @Override
    public void dispatch(final NewMessageReceived event) {
        chats.get(event.getChatId()).addMessage(event.getChatMessage());
    }

    @Override
    public void dispatch(final UserAddedToChat event) {
        final User user = User.from(event.getUsername());
        chats.get(event.getChatId()).connectUser(user);
    }

    @Override
    public void subscribe(final Chat chat){
        chats.put(chat.getId(),chat);
    }
}
