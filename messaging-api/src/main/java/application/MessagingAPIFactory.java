package application;

import domain.ChatRepositoryFactory;
import domain.MessageEventDispatcherFactory;

public class MessagingAPIFactory {

    private static MessagingAPI instance = null;

    private MessagingAPIFactory() {
    }

    public static MessagingAPI createAPI(){
        if(instance == null){
            instance = new StandardMessagingAPI(ChatRepositoryFactory.getInstance(), MessageEventDispatcherFactory.getInstance());
        }
        return instance;
    }
}
