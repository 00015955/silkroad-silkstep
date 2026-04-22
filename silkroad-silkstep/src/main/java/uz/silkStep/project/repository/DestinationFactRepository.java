package uz.silkStep.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.DestinationFact;
import uz.silkStep.project.enums.CommonStatus;

import java.util.List;
import java.util.UUID;


@Repository
public interface DestinationFactRepository extends JpaRepository<DestinationFact, UUID> {
    List<DestinationFact> findAllByDestinationIdOrderBySortOrder(UUID destinationId);

    List<DestinationFact> findAllByDestinationIdAndStatusOrderBySortOrder(UUID destinationId, CommonStatus status);
}
