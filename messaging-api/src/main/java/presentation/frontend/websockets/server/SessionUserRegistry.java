package presentation.frontend.websockets.server;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUserRegistry {
    private Map<String, String> sessionIdToUserMap;
    private Map<String, Session > idToSessionMap;
    private static SessionUserRegistry instance;

    private SessionUserRegistry() {
        this.sessionIdToUserMap = new ConcurrentHashMap<>();
        this.idToSessionMap = new ConcurrentHashMap<>();
    }

    public static SessionUserRegistry createRegistry(){
        if(instance == null){
            instance = new SessionUserRegistry();
        }
        return instance;
    }

    public void addSessionForUser(Session session, String user) {
        if(sessionIdToUserMap.containsKey(user)){
            throw new RuntimeException("Session already created for user " + user);
        }
        sessionIdToUserMap.put(session.getId(),user);
        idToSessionMap.put(session.getId(), session);
    }

    public String getUserFor(final Session session) {
        return sessionIdToUserMap.get(session.getId());
    }

    public void removeSession(final Session session) {
        sessionIdToUserMap.remove(session.getId());
        idToSessionMap.remove(session.getId());
    }
}

