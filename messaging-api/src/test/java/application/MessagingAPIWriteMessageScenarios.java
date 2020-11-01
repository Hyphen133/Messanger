package application;

import domain.Chat;
import domain.MessageBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class MessagingAPIWriteMessageScenarios {

    @Test
    public void createNewChat(){
        //Given
        MessagingAPI messagingAPI = MessagingAPIFactory.createAPI();
        final UUID id = UUID.randomUUID();

        //When
        final Chat chat = messagingAPI.createChatFor(id);

        //Then
        Assert.assertEquals(id, chat.getId());
        Assert.assertEquals(0, chat.getMessages().size());
    }

    @Test
    public void updateChatOnNewMessage(){
        //Given
        MessagingAPI messagingAPI = MessagingAPIFactory.createAPI();
        final UUID id = UUID.randomUUID();
        final String content = "Hello";
        final String author = "Author";
        final Chat chat = messagingAPI.createChatFor(id);
        Message message = MessageBuilder.create().withAuthor(author).withContent(content).forChat(id).build();

        //When
        messagingAPI.write(message);

        //Then
        Assert.assertEquals(1, chat.getMessages().size());
        Assert.assertEquals(content, chat.getMessages().get(0).getContent());
        Assert.assertEquals(author, chat.getMessages().get(0).getAuthor());
    }

}
