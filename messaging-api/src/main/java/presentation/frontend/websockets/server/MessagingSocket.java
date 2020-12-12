package presentation.frontend.websockets.server;

import application.MessagingAPI;
import application.MessagingAPIFactory;
import application.WriteMessage;
import ports.Logger;
import infrastructure.LoggerFactory;
import ports.LoggingType;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@ServerEndpoint(value = "/webSocket/{username}",
        encoders = MessageRepresentationEncoder.class, decoders = MessageRepresentationDecoder.class)
public class MessagingSocket {
    private Logger logger = LoggerFactory.getInstance();
    private Session session;
    private MessagingAPI messagingAPI = MessagingAPIFactory.createAPI();
    private UserSocketRegistry userSocketRegistry = UserSocketRegistry.createRegistry();
    private SessionUserRegistry sessionUserRegistry = SessionUserRegistry.createRegistry();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.session = session;
        logger.log(LoggingType.INFO, "Started new session " + session.getId());
        logger.log(LoggingType.INFO, username + " connected");

        userSocketRegistry.addSessionForUser(this,username);
        sessionUserRegistry.addSessionForUser(session,username);
    }

    @OnMessage //Allows the client to send message to the socket.
    public void onMessage(MessageRepresentation messageRepresentation) {
        logger.log(LoggingType.INFO, "Received " + messageRepresentation.toString());
        messagingAPI.write(WriteMessage.from(UUID.fromString(messageRepresentation.chatId), messageRepresentation.author, messageRepresentation.content));
        broadcastToChat(messageRepresentation);
    }

    private void broadcastToChat(MessageRepresentation message) {
        final List<MessagingSocket> sockets = messagingAPI.getUsersConnectedToChat(UUID.fromString(message.chatId)).stream().filter(user -> userSocketRegistry.hasSocketFor(user.getName()))
                .map(user -> userSocketRegistry.getSocketFor(user.getName())).collect(Collectors.toList());

        logger.log(LoggingType.INFO, "Starting broadcast of " + message.content + " from " + message.author + " for " + String.join(",",messagingAPI.getUsersConnectedToChat(UUID.fromString(message.chatId)).stream().map(x->x.getName()).collect(Collectors.toList())));
        for (MessagingSocket messagingSocket : sockets) {
            logger.log(LoggingType.INFO, "Broadcasting message" + message.content + " to " + messagingSocket.session.getId());
            messagingSocket.sendMessage(message);

        }
    }

    private void sendMessage(MessageRepresentation message) {
        try {
            this.session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            logger.log(LoggingType.ERROR, "Caught exception while sending message to Session Id: " + this.session.getId());
        }
    }

    @OnClose
    public void onClose(Session session) {
        String user = sessionUserRegistry.getUserFor(session);
        logger.log(LoggingType.INFO, "User " +  user  + " with session " + this.session.getId() + " disconnected ");
        sessionUserRegistry.removeSession(session);
        userSocketRegistry.removeUser(user);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        //Error
    }

}
