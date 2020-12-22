package application;


import infrastructure.messaging.local.LocalMessageEventDispatcherFactory;
import infrastructure.persistance.hibernate.HibernateChatRepositoryFactory;
import infrastructure.persistance.hibernate.HibernateConfig;
import infrastructure.persistance.local.LocalChatRepositoryFactory;

public class MessagingAPIFactory {

    private static MessagingAPI instance = null;
    private static ApplicationProfile profile = ApplicationProfile.PRODUCTION;

    private MessagingAPIFactory() {
    }

    public static MessagingAPI createAPI(){
        if(instance == null){
            switch (profile){
                case PRODUCTION:
                    instance = new StandardMessagingAPI(HibernateChatRepositoryFactory.getInstance(), LocalMessageEventDispatcherFactory.getInstance());
                case TESTING:
                    instance = new StandardMessagingAPI(LocalChatRepositoryFactory.getInstance(),LocalMessageEventDispatcherFactory.getInstance());
            }
        }
        return instance;
    }
}