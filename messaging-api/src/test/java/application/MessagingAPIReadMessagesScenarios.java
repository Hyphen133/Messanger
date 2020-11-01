package application;

import domain.Author;
import domain.Chat;
import domain.ChatMessage;
import domain.ChatRepository;
import domain.ChatRepositoryFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

public class MessagingAPIReadMessagesScenarios {
    @Test
    public void shouldReadMessagesForExistingChat(){
        //Given
        MessagingAPI messagingAPI = MessagingAPIFactory.createAPI();
        Chat chat = createChat();

        //When
        final List<ReadMessage> messagesForChat = messagingAPI.getMessagesForChat(chat.getId());

        //Then
        Assert.assertEquals(4, messagesForChat.size());
    }

    private Chat createChat() {
        ChatRepository chatRepository = ChatRepositoryFactory.getInstance();
        UUID chatId = UUID.randomUUID();
        chatRepository.add(Chat.from(chatId));
        Author author1 = Author.from("Author1");
        Author author2 = Author.from("Author2");
        Chat chat = chatRepository.getById(chatId);
        chat.addMessage(ChatMessage.from(author1, "Hello"));
        chat.addMessage(ChatMessage.from(author2, "Hi!!!!"));
        chat.addMessage(ChatMessage.from(author1, "How are u?"));
        chat.addMessage(ChatMessage.from(author2, "Sorry got to go :("));

        return chat;
    }
}
