package application;

import ports.ChatRepository;

public class ChatRepositoryProvider {
    private static ChatRepository chatRepository = null;

    public static void setChatRepository(final ChatRepository chatRepository) {
        ChatRepositoryProvider.chatRepository = chatRepository;
    }

    public static ChatRepository getChatRepository() {
        return chatRepository;
    }
}
