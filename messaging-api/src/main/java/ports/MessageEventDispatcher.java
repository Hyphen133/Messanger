package ports;

import domain.Chat;
import domain.NewMessageReceived;

public interface MessageEventDispatcher {
    void dispatch(NewMessageReceived event);
    void subscribe(Chat chat);
}
