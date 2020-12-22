package infrastructure.persistance.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "chats")
public class ChatRecord {
    @Id
    public UUID id;
}
