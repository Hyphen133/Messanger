package presentation.frontend.websockets;

import infrastructure.Logger;
import infrastructure.LoggerFactory;
import infrastructure.LoggingType;
import org.springframework.stereotype.Component;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by The Geeky Asian on 1/2/2019.
 */
@Component
@ServerEndpoint(value = "/webSocket/{username}/{chatId}",
        encoders = MessageRepresentationEncoder.class, decoders = MessageRepresentationDecoder.class)
public class MessagingSocket {
    private Logger logger = LoggerFactory.getInstance();
    private Session session;
    public static Map<String, Set<MessagingSocket>> chatListeners = new HashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("chatId") String chatId) {
        this.session = session;
        logger.log(LoggingType.INFO, username + " connected to " + chatId);

        if (!chatListeners.containsKey(chatId)) {
            chatListeners.put(chatId, new CopyOnWriteArraySet<>());
        }
        chatListeners.get(chatId).add(this);
    }

    @OnMessage //Allows the client to send message to the socket.
    public void onMessage(MessageRepresentation messageRepresentation) {
        logger.log(LoggingType.INFO, "Received " + messageRepresentation.toString());
        broadcastToChat(messageRepresentation);
    }

    @OnClose
    public void onClose(Session session) {
//        listeners.remove(this);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        //Error
    }

    public void broadcastToChat(MessageRepresentation message) {
        for (MessagingSocket listener : chatListeners.get(message.chatId)) {
            logger.log(LoggingType.INFO, "Broadcasting to " + listener.session.getId());
            listener.sendMessage(message);
        }
    }

    private void sendMessage(MessageRepresentation message) {
        try {
            this.session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            logger.log(LoggingType.ERROR, "Caught exception while sending message to Session Id: " + this.session.getId());
        }
    }

}
