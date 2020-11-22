package domain;

import ports.ChatRepository;
import infrastructure.InMemoryChatRepository;
import ports.ChatRespositoryFactory;

public class LocalChatRepositoryFactory implements ChatRespositoryFactory {

    private static ChatRepository instance = null;

    private LocalChatRepositoryFactory() {
    }

    public static ChatRepository getInstance(){
        if(instance == null){
            instance = new InMemoryChatRepository();
        }
        return instance;
    }

    @Override
    public ChatRepository create() {
        return getInstance();
    }
}
