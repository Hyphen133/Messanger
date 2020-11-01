package domain;

public interface MessageDispatcher {
    void dispatch(NewMessageReceived event);
    void subscribe(Chat chat);
}
