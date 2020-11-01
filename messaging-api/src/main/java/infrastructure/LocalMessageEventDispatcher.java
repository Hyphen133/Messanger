package infrastructure;

import domain.Author;
import domain.Chat;
import domain.ChatMessage;
import domain.MessageEventDispatcher;
import domain.NewMessageReceived;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalMessageEventDispatcher implements MessageEventDispatcher {
    public Map<UUID, Chat> chats;

    public LocalMessageEventDispatcher() {
        chats = new HashMap<>();
    }

    @Override
    public void dispatch(final NewMessageReceived event) {
        final ChatMessage chatMessage = ChatMessage.from(Author.from(event.getAuthor()), event.getContent());
        chats.get(event.getChatId()).addMessage(chatMessage);
    }

    @Override
    public void subscribe(final Chat chat){
        chats.put(chat.getId(),chat);
    }
}
