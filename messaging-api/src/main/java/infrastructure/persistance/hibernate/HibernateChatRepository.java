package infrastructure.persistance.hibernate;

import domain.Chat;
import domain.ChatMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ports.ChatRepository;
import java.util.List;
import java.util.UUID;

public class HibernateChatRepository implements ChatRepository {
    private SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    @Override
    public List<ChatMessage> getMessagesFor(final Chat chat) {
        try(Session session = sessionFactory.openSession()){
            return null;
        }
    }

    @Override
    public void add(final Chat chat) {
        try(Session session = sessionFactory.openSession()){
            session.save(chat);
        }
    }

    @Override
    public Chat getById(final UUID id) {
        try(Session session = sessionFactory.openSession()){
//            final Query<ChatRecord> query = session.createQuery("from chats where id == " + id.toString(), ChatRecord.class);
            return null;
        }
    }

    @Override
    public void save(final UUID chatId, final ChatMessage chatMessage) {

    }
}
