package application;

import domain.Message;
import domain.MessageBuilder;
import org.junit.Test;

public class MessagingAPIWriteMessageScenarios {
    @Test
    public void addNewMessage(){
        //Given
        MessagingAPI messagingAPI = MessagingAPIFactory.createAPI();
        Message message = MessageBuilder.create().withContent("Test").build();

        //When
        messagingAPI.write(message);

        //Then

    }
}
