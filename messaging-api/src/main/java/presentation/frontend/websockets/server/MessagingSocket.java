package presentation.frontend.websockets.server;

import application.ApplicationProfile;
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
    private final Logger logger = LoggerFactory.getInstance();
    private Session session;
    private final MessagingAPI messagingAPI = MessagingAPIFactory.createAPI(ApplicationProfile.PRODUCTION);
    private final UserSocketRegistry userSocketRegistry = UserSocketRegistry.createRegistry();
    private final SessionUserRegistry sessionUserRegistry = SessionUserRegistry.createRegistry();

    @OnOpen
    public void onOpen(final Session session, @PathParam("username") final String username) {
        this.session = session;
        logger.log(LoggingType.INFO, "Started new session " + session.getId());
        logger.log(LoggingType.INFO, username + " connected");

        userSocketRegistry.addSocketForUser(this,username);
        sessionUserRegistry.addSessionForUser(session,username);
    }

    @OnMessage //Allows the client to send message to the socket.
    public void onMessage(final MessageRepresentation messageRepresentation) {
        logger.log(LoggingType.INFO, "Received " + messageRepresentation.toString());
        messagingAPI.write(WriteMessage.from(UUID.fromString(messageRepresentation.chatId), messageRepresentation.author, messageRepresentation.content));
        broadcastToChat(messageRepresentation);
    }

    private void broadcastToChat(final MessageRepresentation message) {
        final List<MessagingSocket> sockets = messagingAPI.getUsersConnectedToChat(UUID.fromString(message.chatId)).stream().filter(user -> userSocketRegistry.hasSocketFor(user.getName()))
                .map(user -> userSocketRegistry.getSocketFor(user.getName())).collect(Collectors.toList());

        logger.log(LoggingType.INFO, "Starting broadcast of " + message.content + " from " + message.author + " for " + String.join(",",messagingAPI.getUsersConnectedToChat(UUID.fromString(message.chatId)).stream().map(x->x.getName()).collect(Collectors.toList())));
        for (final MessagingSocket messagingSocket : sockets) {
            logger.log(LoggingType.INFO, "Broadcasting message" + message.content + " to " + messagingSocket.session.getId());
            messagingSocket.sendMessage(message);
        }
    }

    private void sendMessage(final MessageRepresentation message) {
        this.session.getAsyncRemote().sendObject(message);
    }

    @OnClose
    public void onClose(final Session session) {
        final String user = sessionUserRegistry.getUserFor(session);
        logger.log(LoggingType.INFO, "User " +  user  + " with session " + this.session.getId() + " disconnected ");
        sessionUserRegistry.removeSession(session);
        userSocketRegistry.removeUser(user);
    }

    @OnError
    public void onError(final Session session, final Throwable throwable) {
        logger.log(LoggingType.ERROR, "Error for " + session.getId() + " caused by: " + throwable.getMessage());
        throwable.printStackTrace();
    }

}
