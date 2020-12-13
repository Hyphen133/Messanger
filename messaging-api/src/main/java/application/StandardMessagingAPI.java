package application;

import domain.Author;
import domain.Chat;
import domain.ChatMessage;
import domain.User;
import domain.UserAddedToChat;
import ports.ChatRepository;
import ports.MessageEventDispatcher;
import domain.NewMessageReceived;
import ports.Logger;
import infrastructure.LoggerFactory;
import ports.LoggingType;
import presentation.ApplicationBeanInitializationListener;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

final class StandardMessagingAPI implements MessagingAPI {
    public final MessageEventDispatcher dispatcher;
    private final Logger logger = LoggerFactory.getInstance();

    public StandardMessagingAPI(MessageEventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void write(final WriteMessage writeMessage) {
        logger.log(LoggingType.INFO, "Writing message " + writeMessage.toString());
        final ChatMessage chatMessage = ChatMessage.from(Author.from(writeMessage.getAuthor()), writeMessage.getContent());
        final NewMessageReceived event = NewMessageReceived.from(writeMessage.getChatId(),chatMessage);
        getChatRepository().save(writeMessage.getChatId(), chatMessage);
        dispatcher.dispatch(event);
    }

    @Override
    public Chat createChatFor(final UUID chatId) {
        logger.log(LoggingType.INFO, "Creating chat with id " + chatId.toString());
        final Chat chat = Chat.from(chatId);
        getChatRepository().add(chat);
        dispatcher.subscribe(chat);
        return chat;
    }

    private ChatRepository getChatRepository() {
        return ChatRepositoryProvider.getChatRepository();
    }

    @Override
    public List<ReadMessage> getMessagesForChat(final UUID chatId) {
        return getChatRepository().getById(chatId)
                .getMessages()
                .stream()
                .map(chatMessage-> ReadMessage.from(chatMessage.getAuthor().getNickname(),chatMessage.getContent()))
                .collect(Collectors.toList());
    }

    @Override
    public void connectUserToChat(final String username, final UUID chatId) {
        UserAddedToChat event = UserAddedToChat.from(username, chatId);
        dispatcher.dispatch(event);
    }

    @Override
    public Set<User> getUsersConnectedToChat(final UUID chatId) {
        return getChatRepository().getById(chatId).getConnectedUsers();
    }
}
