package application;

import domain.Chat;
import domain.LocalMessageDispatcher;
import domain.Message;
import domain.MessageDispatcher;
import domain.NewMessageReceived;

import java.util.UUID;

final class StandardMessagingAPI implements MessagingAPI {
    public MessageDispatcher dispatcher;

    public StandardMessagingAPI() {
        dispatcher = new LocalMessageDispatcher();
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
