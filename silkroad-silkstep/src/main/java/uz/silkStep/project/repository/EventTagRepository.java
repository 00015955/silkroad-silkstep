package uz.silkStep.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.EventTag;
import uz.silkStep.project.enums.CommonStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventTagRepository extends JpaRepository<EventTag, UUID> {
    List<EventTag> findAllByEventIdOrderById(UUID eventId);

    List<EventTag> findAllByEventIdAndStatusOrderById(UUID eventId, CommonStatus status);
}
