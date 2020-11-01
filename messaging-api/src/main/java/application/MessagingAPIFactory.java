package application;

import domain.ChatRepository;
import infrastructure.InMemoryChatRepository;

public class MessagingAPIFactory {

    private static MessagingAPI instance = null;

    private MessagingAPIFactory() {
    }

    public static MessagingAPI createAPI(){
        if(instance == null){
            instance = new StandardMessagingAPI();
        }
        return instance;
    }
}
