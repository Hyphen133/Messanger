package application;

import domain.Chat;
import ports.ChatRepository;
import ports.MessageEventDispatcher;
import domain.NewMessageReceived;
import ports.Logger;
import infrastructure.LoggerFactory;
import ports.LoggingType;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

final class StandardMessagingAPI implements MessagingAPI {
    private final ChatRepository chatRepository;
    public final MessageEventDispatcher dispatcher;
    private final Logger logger = LoggerFactory.getInstance();

    public StandardMessagingAPI(ChatRepository chatRepository, MessageEventDispatcher dispatcher) {
        this.chatRepository = chatRepository;
        this.dispatcher = dispatcher;
    }

    @Override
    public void write(final WriteMessage writeMessage) {
        logger.log(LoggingType.INFO, "Writing message " + writeMessage.toString());
        final NewMessageReceived event = NewMessageReceived.from(writeMessage);
        dispatcher.dispatch(event);
    }

    @Override
    public Chat createChatFor(final UUID chatId) {
        logger.log(LoggingType.INFO, "Creating chat with id " + chatId.toString());
        final Chat chat = Chat.from(chatId);
        chatRepository.add(chat);
        dispatcher.subscribe(chat);
        return chat;
    }

    @Override
    public List<ReadMessage> getMessagesForChat(final UUID chatId) {
        return chatRepository.getById(chatId)
                .getMessages()
                .stream()
                .map(chatMessage-> ReadMessage.from(chatMessage.getAuthor().getNickname(),chatMessage.getContent()))
                .collect(Collectors.toList());
    }
}
