package presentation.frontend.websockets.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserSocketRegistry {
    private final Map<String, MessagingSocket> userSocketMap;
    private static UserSocketRegistry instance;

    private UserSocketRegistry() {
        this.userSocketMap = new ConcurrentHashMap<>();
    }

    public static UserSocketRegistry createRegistry(){
        if(instance == null){
            instance = new UserSocketRegistry();
        }
        return instance;
    }

    public void addSocketForUser(final MessagingSocket socket, final String user) {
        if(userSocketMap.containsKey(user)){
            throw new RuntimeException("Session already created for user " + user);
        }
        userSocketMap.put(user,socket);
    }

    public boolean hasSocketFor(final String name) {
        return userSocketMap.containsKey(name);
    }

    public MessagingSocket getSocketFor(final String name) {
        return userSocketMap.get(name);
    }

    public void removeUser(final String user) {
        userSocketMap.remove(user);
    }
}
