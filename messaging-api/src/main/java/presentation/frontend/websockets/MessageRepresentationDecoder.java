package presentation.frontend.websockets;

import com.google.gson.Gson;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageRepresentationDecoder implements Decoder.Text<MessageRepresentation> {

    private static Gson gson = new Gson();

    @Override
    public MessageRepresentation decode(String s) throws DecodeException {
        return gson.fromJson(s, MessageRepresentation.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}