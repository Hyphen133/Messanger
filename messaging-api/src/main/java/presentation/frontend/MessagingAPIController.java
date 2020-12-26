package presentation.frontend;

import static org.springframework.http.HttpStatus.OK;

import application.ApplicationProfile;
import application.MessagingAPI;
import application.MessagingAPIFactory;
import application.ReadMessage;
import domain.ChatMessage;
import infrastructure.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ports.Logger;
import ports.LoggingType;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
public class MessagingAPIController {

    private final MessagingAPI messagingAPI = MessagingAPIFactory.createAPI(ApplicationProfile.PRODUCTION);
    private final Logger logger = LoggerFactory.getInstance();

    @GetMapping("/messagesForChat/{id}")
    public List<ReadMessage> getMessagesForChat(@PathVariable("id") final String chatId){
        return messagingAPI.getMessagesForChat(UUID.fromString(chatId));
    }

    @GetMapping("/status")
    public String status(){
        return "Running";
    }

    @PostMapping("/createChat")
    @ResponseStatus(value = OK)
    public void createChat(@RequestBody final ChatCreationRequestBody chatCreationRequestBody){
        messagingAPI.createChatFor(UUID.fromString(chatCreationRequestBody.chatId));
    }

    @PostMapping("/connectUserToChat")
    @ResponseStatus(value = OK)
    public void connectUserToChat(@RequestBody final UserConnectToChatRequestBody userConnectToChatRequestBody){
        logger.log(LoggingType.INFO, "User " + userConnectToChatRequestBody.username + " connected to chat " + userConnectToChatRequestBody.chatId);
        messagingAPI.connectUserToChat(userConnectToChatRequestBody.username, UUID.fromString(userConnectToChatRequestBody.chatId));
    }
}

