package presentation.frontend;


import application.MessagingAPI;
import application.MessagingAPIFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingAPIConfiguration {
    @Bean
    public MessagingAPI messagingAPI(){
        return MessagingAPIFactory.createAPI();
    }

}
