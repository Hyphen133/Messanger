package domain;

public class MessageDispatcherFactory {
    public static MessageDispatcher create(){
        return new LocalMessageDispatcher();
    }
}
