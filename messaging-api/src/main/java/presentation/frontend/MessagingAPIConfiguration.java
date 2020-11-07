package presentation.frontend;


import application.MessagingAPI;
import application.MessagingAPIFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class MessagingAPIConfiguration {
    @Bean
    public MessagingAPI messagingAPI() {
        return MessagingAPIFactory.createAPI();
    }

    //Required to process MessagingSocket endpoint 
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
