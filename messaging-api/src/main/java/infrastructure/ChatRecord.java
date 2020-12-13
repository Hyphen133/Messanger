package infrastructure;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class ChatRecord {
    @Id
    public UUID id;
}
