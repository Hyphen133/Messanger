package infrastructure.persistance.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chatUsers")
public class ChatUsersRecord {
    @Id
    @GeneratedValue
    public Long id;
    public UUID chatId;
    @OneToMany
    public List<UserRecord> users;
}
