package application;

import domain.Message;

public interface MessagingAPI {
    void write(Message message);
}
