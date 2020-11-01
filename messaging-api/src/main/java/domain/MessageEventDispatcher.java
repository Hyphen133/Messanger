package domain;

public interface MessageEventDispatcher {
    void dispatch(NewMessageReceived event);
    void subscribe(Chat chat);
}
