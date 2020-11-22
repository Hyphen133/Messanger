package ports;

import domain.Chat;
import domain.NewMessageReceived;
import domain.UserAddedToChat;

public interface MessageEventDispatcher {
    void dispatch(NewMessageReceived event);
    void dispatch(UserAddedToChat event);

    void subscribe(Chat chat);
}
