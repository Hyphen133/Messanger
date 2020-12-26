package presentation.frontend.websockets.server;

import com.google.gson.Gson;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageRepresentationEncoder implements Encoder.Text<MessageRepresentation> {

    private static final Gson gson = new Gson();

    @Override
    public String encode(final MessageRepresentation messageRepresentation) throws EncodeException {
        return gson.toJson(messageRepresentation);
    }

    @Override
    public void init(final EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}

