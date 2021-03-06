package application;

import domain.Chat;
import infrastructure.persistance.local.LocalChatRepositoryFactory;
import ports.ChatRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class MessagingAPIWriteMessageScenariosTests {

    @Test
    public void createNewChat(){
        //Given
        final MessagingAPI messagingAPI = TestMessagingAPIFactory.createTestAPI();
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
        final MessagingAPI messagingAPI = TestMessagingAPIFactory.createTestAPI();
        final UUID id = UUID.randomUUID();
        final String content = "Hello";
        final String author = "Author";
        final Chat chat = messagingAPI.createChatFor(id);
        final WriteMessage writeMessage = WriteMessageBuilder.create().withAuthor(author).withContent(content).forChat(id).build();

        //When
        messagingAPI.write(writeMessage);

        //Then
        Assert.assertEquals(1, chat.getMessages().size());
        Assert.assertEquals(content, chat.getMessages().get(0).getContent());
        Assert.assertEquals(author, chat.getMessages().get(0).getAuthor().getNickname());
    }

    @Test
    public void createNewChatShouldBePersisted(){
        //Given
        final MessagingAPI messagingAPI = TestMessagingAPIFactory.createTestAPI();
        final ChatRepository chatRepository = LocalChatRepositoryFactory.getInstance();
        final UUID chatId = UUID.randomUUID();

        //When
        messagingAPI.createChatFor(chatId);

        //Then
        Assert.assertNotEquals(null, chatRepository.getById(chatId));
    }

    @Test
    public void addUserToChat(){
        //Given
        final MessagingAPI messagingAPI = TestMessagingAPIFactory.createTestAPI();
        final String username = "John";
        final UUID chatId = UUID.randomUUID();
        messagingAPI.createChatFor(chatId);

        //When
        messagingAPI.connectUserToChat(username, chatId);

        //Then
        Assert.assertEquals(1, messagingAPI.getUsersConnectedToChat(chatId).size());
    }
}
