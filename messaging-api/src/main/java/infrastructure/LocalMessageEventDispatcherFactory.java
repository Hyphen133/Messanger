package infrastructure;

import infrastructure.LocalMessageEventDispatcher;
import ports.MessageEventDispatcher;
import ports.MessageEventDispatcherFactory;

public class LocalMessageEventDispatcherFactory implements MessageEventDispatcherFactory {
    private static MessageEventDispatcher instance = null;

    private LocalMessageEventDispatcherFactory() {
    }

    public static MessageEventDispatcher getInstance(){
        if(instance == null){
            instance = new LocalMessageEventDispatcher();
        }
        return instance;
    }

    @Override
    public MessageEventDispatcher create() {
        return getInstance();
    }
}
