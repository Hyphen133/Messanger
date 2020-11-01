package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalMessageDispatcher implements MessageDispatcher {
    public Map<UUID, Chat> chats;

    public LocalMessageDispatcher() {
        chats = new HashMap<>();
    }

    @Override
    public void dispatch(NewMessageReceived event) {
        ChatMessage chatMessage = ChatMessage.from(event.getChatId(), event.getContent());
        chats.get(event.getChatId()).addMessage(chatMessage);
    }

    @Override
    public void subscribe(Chat chat){
        chats.put(chat.getId(),chat);
    }
}
