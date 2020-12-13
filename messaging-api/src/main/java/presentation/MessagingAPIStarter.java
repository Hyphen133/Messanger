package presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ports.ChatRepository;

@SpringBootApplication
public class MessagingAPIStarter {
    public static void main(String[] args) {
        SpringApplication.run(MessagingAPIStarter.class);
    }
}
