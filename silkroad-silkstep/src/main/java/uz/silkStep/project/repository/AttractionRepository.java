package uz.silkStep.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.Attraction;
import uz.silkStep.project.enums.CommonStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, UUID> {

    List<Attraction> findAllByDestinationIdOrderBySortOrderAsc(UUID destinationId);

    List<Attraction> findAllByDestinationIdAndStatusOrderBySortOrderAsc(UUID destinationId, CommonStatus status);
}

// This interface extends JpaRepository, which provides basic CRUD operations for the Attraction entity.