package domain;

import domain.ChatRepository;
import infrastructure.InMemoryChatRepository;

public class ChatRepositoryFactory {

    private static ChatRepository instance = null;

    private ChatRepositoryFactory() {
    }

    public static ChatRepository getInstance(){
        if(instance == null){
            instance = new InMemoryChatRepository();
        }
        return instance;
    }
}
