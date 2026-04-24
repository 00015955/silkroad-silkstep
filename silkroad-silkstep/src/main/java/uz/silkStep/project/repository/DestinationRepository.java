package uz.silkStep.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.Destination;
import uz.silkStep.project.enums.CommonStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DestinationRepository extends JpaRepository<Destination, UUID> {

    Optional<Destination> findBySlug(String slug);

    List<Destination> findAllByStatusOrderBySortOrderAsc(CommonStatus status);

    boolean existsBySlug(String slug);

    @Query(value = "SELECT count(d.id) FROM destination d WHERE d.status = 'ACTIVE' AND d.created_at >= DATE_TRUNC('month', NOW())", nativeQuery = true)
    long countByBetweenDate();
}

// This interface extends JpaRepository, which provides basic CRUD operations for the Destination entity.