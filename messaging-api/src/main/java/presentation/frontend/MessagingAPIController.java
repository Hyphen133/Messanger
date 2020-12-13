package presentation.frontend;

import application.MessagingAPI;
import application.MessagingAPIFactory;
import application.ReadMessage;
import application.WriteMessage;
import infrastructure.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ports.Logger;
import ports.LoggingType;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin("*")
@RestController
public class MessagingAPIController {

    private MessagingAPI messagingAPI = MessagingAPIFactory.createAPI();
    private Logger logger = LoggerFactory.getInstance();

    @GetMapping("/messagesForChat/{id}")
    public List<ReadMessage> getMessagesForChat(@PathVariable("id") String chatId){
        return messagingAPI.getMessagesForChat(UUID.fromString(chatId));
    }

    @GetMapping("/status")
    public String status(){
        return "Running";
    }

    @PostMapping("/createChat")
    @ResponseStatus(value = OK)
    public void createChat(@RequestBody ChatCreationRequestBody chatCreationRequestBody){
        messagingAPI.createChatFor(UUID.fromString(chatCreationRequestBody.chatId));
    }

    @PostMapping("/connectUserToChat")
    @ResponseStatus(value = OK)
    public void connectUserToChat(@RequestBody UserConnectToChatRequestBody userConnectToChatRequestBody){
        logger.log(LoggingType.INFO, "User " + userConnectToChatRequestBody.username + " connected to chat " + userConnectToChatRequestBody.chatId);
        messagingAPI.connectUserToChat(userConnectToChatRequestBody.username, UUID.fromString(userConnectToChatRequestBody.chatId));
    }
}

