package presentation;

import application.ChatRepositoryProvider;
import infrastructure.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ports.ChatRepository;
import ports.Logger;
import ports.LoggingType;


/*
*   This class helps to decouple application from Spring JPA
 */
@Component
public class ApplicationBeanInitializationListener implements ApplicationListener {

    private Logger logger = LoggerFactory.getInstance();

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();

            ChatRepository chatRepository = applicationContext.getBean(ChatRepository.class);
            ChatRepositoryProvider.setChatRepository(chatRepository);

            logger.log(LoggingType.INFO, "FINISHED TO INSTANTIATE APP CONTEXT");
        }
    }

}