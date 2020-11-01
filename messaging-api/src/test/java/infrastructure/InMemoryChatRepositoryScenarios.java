package infrastructure;

import domain.Chat;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class InMemoryChatRepositoryScenarios {
    @Test
    public void shouldAddChatToRepository(){
        //Given
        final InMemoryChatRepository chatRepository = new InMemoryChatRepository();
        final UUID chatId = UUID.randomUUID();

        //When
        chatRepository.add(Chat.from(chatId));

        //Then
        Assert.assertEquals(chatId, chatRepository.getById(chatId).getId());
    }

    @Test
    public void shouldThrowExceptionOnTryingToAccessChatNotInRepository(){
        //Given
        final InMemoryChatRepository chatRepository = new InMemoryChatRepository();

        //When
        boolean wasCaught = false;
        try{
            chatRepository.getById(UUID.randomUUID());
        }catch (RuntimeException e){
            wasCaught = true;
        }

        //Then
        Assert.assertTrue(wasCaught);

    }
}
