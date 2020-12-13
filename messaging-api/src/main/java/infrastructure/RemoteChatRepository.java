package infrastructure;

import domain.Chat;
import domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ports.ChatRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RemoteChatRepository implements ChatRepository {
    @Autowired
    JpaMessageRepository messageRepository;

    @Autowired
    JpaChatRepository chatRepository;

    @Override
    public List<ChatMessage> getMessagesFor(final Chat chat) {
        UUID chatId = chat.getId();
        return getMessages(chatId);
    }

    private List<ChatMessage> getMessages(final UUID chatId) {
        return messageRepository.findByChatId(chatId).stream().map(MessageRecord::toChatMessage).collect(Collectors.toList());
    }

    @Override
    public void add(final Chat chat) {
        ChatRecord record = new ChatRecord();
        record.id = chat.getId();
        chatRepository.save(record);
    }

    @Override
    public Chat getById(final UUID id) {
        List<ChatMessage> messages = getMessages(id);
        return Chat.from(id,messages);
    }

    @Override
    public void save(final UUID chatId, final ChatMessage chatMessage) {
        MessageRecord record = new MessageRecord();
        record.author = chatMessage.getAuthor().getNickname();
        record.content = chatMessage.getContent();
        record.chatId = chatId;
        messageRepository.save(record);
    }
}
