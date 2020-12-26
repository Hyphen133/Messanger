package application;

import domain.Author;
import domain.Chat;
import domain.ChatMessage;
import domain.NewMessageReceived;
import domain.User;
import infrastructure.LoggerFactory;
import ports.ChatRepository;
import ports.Logger;
import ports.LoggingType;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

final class StandardMessagingAPI implements MessagingAPI {
    private final ChatRepository chatRepository;
    private final Logger logger = LoggerFactory.getInstance();

    public StandardMessagingAPI(final ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public void write(final WriteMessage writeMessage) {
        logger.log(LoggingType.INFO, "Writing message " + writeMessage.toString());
        final ChatMessage chatMessage = ChatMessage.from(Author.from(writeMessage.getAuthor()), writeMessage.getContent());
        final NewMessageReceived event = NewMessageReceived.from(writeMessage.getChatId(),chatMessage);
        chatRepository.save(writeMessage.getChatId(), chatMessage);
    }

    @Override
    public Chat createChatFor(final UUID chatId) {
        logger.log(LoggingType.INFO, "Creating chat with id " + chatId.toString());
        final Chat chat = Chat.from(chatId);
        chatRepository.add(chat);
        return chat;
    }

    @Override
    public List<ReadMessage> getMessagesForChat(final UUID chatId) {
        return chatRepository.getById(chatId)
                .getMessages()
                .stream()
                .map(ReadMessage::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadMessage> connectUserToChat(final String username, final UUID chatId) {
        return chatRepository.getMessagesFor(chatId)
                .stream()
                .map(ReadMessage::from)
                .collect(Collectors.toList());
    }

    @Override
    public Set<User> getUsersConnectedToChat(final UUID chatId) {
        return chatRepository.getById(chatId).getConnectedUsers();
    }
}
