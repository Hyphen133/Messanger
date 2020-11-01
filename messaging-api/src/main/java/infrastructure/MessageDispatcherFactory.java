package infrastructure;

import domain.MessageEventDispatcher;
import infrastructure.LocalMessageEventDispatcher;

public class MessageDispatcherFactory {
    public static MessageEventDispatcher create(){
        return new LocalMessageEventDispatcher();
    }
}
