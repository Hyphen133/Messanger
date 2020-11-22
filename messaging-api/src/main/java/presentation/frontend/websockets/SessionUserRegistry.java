package presentation.frontend.websockets;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUserRegistry {
    private Map<Session, String> sessionUserMap;
    private static SessionUserRegistry instance;

    private SessionUserRegistry() {
        this.sessionUserMap = new ConcurrentHashMap<>();
    }

    public static SessionUserRegistry createRegistry(){
        if(instance == null){
            instance = new SessionUserRegistry();
        }
        return instance;
    }

    public void addSessionForUser(Session session, String user) {
        if(sessionUserMap.containsKey(user)){
            throw new RuntimeException("Session already created for user " + user);
        }
        sessionUserMap.put(session,user);
    }

    public String getUserFor(final Session session) {
        return sessionUserMap.get(session);
    }

    public void removeSession(final Session session) {
        sessionUserMap.remove(session);
    }
}

