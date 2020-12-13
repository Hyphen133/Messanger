package infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface JpaMessageRepository extends JpaRepository<MessageRecord, Long> {
    List<MessageRecord> findByChatId(UUID chatId);
}
