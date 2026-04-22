package uz.silkStep.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.TripPlan;

import java.util.List;
import java.util.UUID;

@Repository
public interface TripPlanRepository extends JpaRepository<TripPlan, UUID> {

    List<TripPlan> findAllByDestinationIdOrderByIdAsc(UUID destinationId);
}
