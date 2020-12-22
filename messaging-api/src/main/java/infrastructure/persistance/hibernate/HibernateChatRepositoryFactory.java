package infrastructure.persistance.hibernate;

import infrastructure.persistance.local.InMemoryChatRepository;
import ports.ChatRepository;
import ports.ChatRespositoryFactory;

public class HibernateChatRepositoryFactory implements ChatRespositoryFactory {
    private static ChatRepository instance = null;

    private HibernateChatRepositoryFactory() {
    }

    public static ChatRepository getInstance(){
        if(instance == null){
            HibernateConfig.getSessionFactory();
            instance = new HibernateChatRepository();
        }
        return instance;
    }

    @Override
    public ChatRepository create() {
        return getInstance();
    }
}
