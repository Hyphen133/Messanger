package infrastructure;

import domain.Author;
import domain.ChatMessage;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class MessageRecord {
    @Id @GeneratedValue
    public Long id;
    public UUID chatId;
    public String author;
    public String content;

    public ChatMessage toChatMessage(){
        return ChatMessage.from(Author.from(author), content);
    }
}
