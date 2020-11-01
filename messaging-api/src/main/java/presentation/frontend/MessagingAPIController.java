package presentation.frontend;

import application.MessagingAPI;
import application.ReadMessage;
import application.WriteMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class MessagingAPIController {

    @Autowired
    private MessagingAPI messagingAPI;

    //http://localhost:8080/messagesForChat/5229ff98-2b23-4fa9-892c-55448f0c63c4
    @GetMapping("/messagesForChat/{id}")
    public List<ReadMessage> getMessagesForChat(@PathVariable("id") String chatId){
        return messagingAPI.getMessagesForChat(UUID.fromString(chatId));
    }

    //http://localhost:8080/status
    @GetMapping("/status")
    public String status(){
        return "Running";
    }

    //http://localhost:8080/writeMessage
    /*
    {
	"chatId" : "5229ff98-2b23-4fa9-892c-55448f0c63c4",
	"author" : "John" ,
	"content" : "Hello"
    }
     */
    @PostMapping("/writeMessage")
    @ResponseStatus(value = OK)
    public void writeMessage(@RequestBody WriteMessageRepresentation writeMessageRepresentation){
        messagingAPI.write(WriteMessage.from(writeMessageRepresentation.chatId, writeMessageRepresentation.author, writeMessageRepresentation.content));
    }
}
