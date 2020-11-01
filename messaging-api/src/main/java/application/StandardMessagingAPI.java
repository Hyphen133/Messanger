package application;

import domain.Chat;
import domain.ChatRepository;
import domain.ChatRepositoryFactory;
import domain.MessageEventDispatcherFactory;
import domain.MessageEventDispatcher;
import domain.NewMessageReceived;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

final class StandardMessagingAPI implements MessagingAPI {
    private final ChatRepository chatRepository;
    public final MessageEventDispatcher dispatcher;

    public StandardMessagingAPI() {
        chatRepository = ChatRepositoryFactory.getInstance();
        dispatcher = MessageEventDispatcherFactory.getInstance();
    }

    @Override
    public void write(WriteMessage writeMessage) {
        NewMessageReceived event = NewMessageReceived.from(writeMessage);
        dispatcher.dispatch(event);
    }

    @Override
    public Chat createChatFor(UUID id) {
        Chat chat = Chat.from(id);
        dispatcher.subscribe(chat);
        return chat;
    }

    @Override
    public List<ReadMessage> getMessagesForChat(UUID chatId) {
        return chatRepository.getById(chatId)
                .getMessages()
                .stream()
                .map(chatMessage-> ReadMessage.from(chatMessage.getAuthor().getNickname(),chatMessage.getContent()))
                .collect(Collectors.toList());
    }
}
