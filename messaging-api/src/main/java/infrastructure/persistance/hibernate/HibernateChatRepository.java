package infrastructure.persistance.hibernate;

import domain.Author;
import domain.Chat;
import domain.ChatMessage;
import domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ports.ChatRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    public Chat getById(final UUID chatId) {
        try(final Session session = sessionFactory.openSession()){
            final Chat chat = Chat.from(chatId, queryMessagesForChat(session,chatId));

            final Optional<ChatUsersRecord> chatUsersRecordOptional = getChatUsersRecords(session, chatId);

            if(chatUsersRecordOptional.isPresent()){
                final ChatUsersRecord chatUsersRecord = chatUsersRecordOptional.get();

                for (UserRecord user : chatUsersRecord.users) {
                    chat.connectUser(User.from(user.name));
                }
            }

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

    @Override
    public void addUserToChat(final User user, final UUID chatId) {
        try(final Session session = sessionFactory.openSession()){
            final Optional<ChatUsersRecord> resultList = getChatUsersRecords(session, chatId);

            if(!resultList.isPresent()){
                ChatUsersRecord chatUsersRecord = new ChatUsersRecord();
                chatUsersRecord.chatId = chatId;
                UserRecord userRecord = new UserRecord();
                userRecord.name = user.getName();
                chatUsersRecord.users = Collections.singletonList(userRecord);
                session.save(userRecord);
                session.save(chatUsersRecord);
            }
        }
    }

    private Optional<ChatUsersRecord> getChatUsersRecords(final Session session, final UUID chatId) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<ChatUsersRecord> cr = criteriaBuilder.createQuery(ChatUsersRecord.class);
        final Root<ChatUsersRecord> root = cr.from(ChatUsersRecord.class);
        cr.select(root).where(criteriaBuilder.equal(root.get("chatId"), chatId));
        final Query<ChatUsersRecord> query = session.createQuery(cr);
        query.setMaxResults(1);
        final List<ChatUsersRecord> resultList = query.getResultList();
        return resultList.stream().findFirst();
    }
}
