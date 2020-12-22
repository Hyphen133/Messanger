package infrastructure.persistance.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserRecord {
    @Id
    @GeneratedValue
    public Long id;

    public String name;
}
