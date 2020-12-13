package application;

import infrastructure.LocalMessageEventDispatcherFactory;


public class MessagingAPIFactory {

    private static MessagingAPI instance = null;

    private MessagingAPIFactory() {
    }

    public static MessagingAPI createAPI(){
        if(instance == null){
            instance = new StandardMessagingAPI(LocalMessageEventDispatcherFactory.getInstance());
        }
        return instance;
    }
}