package infrastructure;

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
    public void dispatch(NewMessageReceived event) {
        ChatMessage chatMessage = ChatMessage.from(event.getChatId(), event.getAuthor(), event.getContent());
        chats.get(event.getChatId()).addMessage(chatMessage);
    }

    @Override
    public void subscribe(Chat chat){
        chats.put(chat.getId(),chat);
    }
}
