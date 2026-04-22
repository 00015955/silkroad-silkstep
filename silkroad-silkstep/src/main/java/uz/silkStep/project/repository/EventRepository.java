package uz.silkStep.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.Event;
import uz.silkStep.project.enums.CommonStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findAllByStatusOrderBySortOrderAsc(CommonStatus status);

    @Query(value = "SELECT count(e.id) FROM events e WHERE e.status = 'ACTIVE' AND e.created_at >= DATE_TRUNC('month', NOW())", nativeQuery = true)
    long countByBetweenDate();
}
