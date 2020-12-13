package infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaChatRepository extends JpaRepository<ChatRecord, UUID> {
}
