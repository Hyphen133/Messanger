package presentation;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Test;
import presentation.frontend.websockets.server.MessagingSocket;
import presentation.frontend.websockets.server.SessionUserRegistry;
import presentation.frontend.websockets.server.UserSocketRegistry;
import javax.websocket.Session;
import java.util.UUID;

public class WebSocketTests {
    @Test
    public void shouldRemoveUsersOnClosingConnection(){
        //Given
        final MessagingSocket socket = new MessagingSocket();
        final String sessionId = UUID.randomUUID().toString();

        final Session session = mock(Session.class);
        when(session.getId()).thenReturn(sessionId);

        final Session session1 = mock(Session.class);
        when(session1.getId()).thenReturn(sessionId);


        //When
        socket.onOpen(session, "TestUser");
        socket.onClose(session1);

        //Then
        Assert.assertFalse(UserSocketRegistry.createRegistry().hasSocketFor("TestUser"));
        Assert.assertEquals(null, SessionUserRegistry.createRegistry().getUserFor(session));
    }
}
