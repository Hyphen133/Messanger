package application;


import infrastructure.persistance.hibernate.HibernateChatRepositoryFactory;
import infrastructure.persistance.local.LocalChatRepositoryFactory;

public class MessagingAPIFactory {

    private static MessagingAPI instance = null;
    private static final ApplicationProfile profile = ApplicationProfile.PRODUCTION;

    private MessagingAPIFactory() {
    }

    public static MessagingAPI createAPI(){
        if(instance == null){
            switch (profile){
                case PRODUCTION:
                    instance = new StandardMessagingAPI(HibernateChatRepositoryFactory.getInstance());
                case TESTING:
                    instance = new StandardMessagingAPI(LocalChatRepositoryFactory.getInstance());
            }
        }
        return instance;
    }
}