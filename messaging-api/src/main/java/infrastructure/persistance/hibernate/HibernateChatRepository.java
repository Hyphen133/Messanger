package infrastructure.persistance.hibernate;

import domain.Author;
import domain.Chat;
import domain.ChatMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ports.ChatRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HibernateChatRepository implements ChatRepository {
    private final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    @Override
    public List<ChatMessage> getMessagesFor(final UUID chatId) {
        try(final Session session = sessionFactory.openSession()){
            return queryMessagesForChat(session, chatId);
        }
    }

    private List<ChatMessage> queryMessagesForChat(final Session session, final UUID chatId) {
        final List<MessageRecord> messageRecords = session.createQuery("SELECT * FROM messages M WHERE M.id = " + chatId, MessageRecord.class).list();
        return messageRecords.stream().map(msg -> ChatMessage.from(Author.from(msg.author),msg.content)).collect(Collectors.toList());
    }

    @Override
    public void add(final Chat chat) {
//        try(Session session = sessionFactory.openSession()){
//            ChatRecord record = new ChatRecord();
//            record.id = chat.getId();
//            session.save(record);
//        }
    }

    @Override
    public Chat getById(final UUID id) {
        try(final Session session = sessionFactory.openSession()){
//            final ChatRecord chatRecord = session.get(ChatRecord.class, id);
            final Chat chat = Chat.from(id, queryMessagesForChat(session,id));
            return chat;
        }
    }

    @Override
    public void save(final UUID chatId, final ChatMessage chatMessage) {
        try(final Session session = sessionFactory.openSession()){
            final MessageRecord messageRecord = new MessageRecord();
            messageRecord.chatId = chatId;
            messageRecord.author = chatMessage.getAuthor().getNickname();
            messageRecord.content = chatMessage.getContent();

            session.save(messageRecord);
        }
    }
}
