package domain;

import infrastructure.LocalMessageEventDispatcher;

public class MessageEventDispatcherFactory {
    private static MessageEventDispatcher instance = null;

    private MessageEventDispatcherFactory() {
    }

    public static MessageEventDispatcher getInstance(){
        if(instance == null){
            instance = new LocalMessageEventDispatcher();
        }
        return instance;
    }
}
