package uz.silkStep.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.Message;
import uz.silkStep.project.enums.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    Optional<Message> findByMessageKeyAndLanguage(String messageKey, Language language);

    List<Message> findByMessageKey(String messageKey);
}