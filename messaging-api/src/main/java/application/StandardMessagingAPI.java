package application;

import domain.Chat;
import infrastructure.LocalMessageEventDispatcher;
import domain.Message;
import domain.MessageEventDispatcher;
import domain.NewMessageReceived;

import java.util.UUID;

final class StandardMessagingAPI implements MessagingAPI {
    public MessageEventDispatcher dispatcher;

    public StandardMessagingAPI() {
        dispatcher = new LocalMessageEventDispatcher();
    }

    @Override
    public void write(Message message) {
        NewMessageReceived event = NewMessageReceived.from(message);
        dispatcher.dispatch(event);
    }

    @Override
    public Chat createChatFor(UUID id) {
        Chat chat = Chat.from(id);
        dispatcher.subscribe(chat);
        return chat;
    }
}
