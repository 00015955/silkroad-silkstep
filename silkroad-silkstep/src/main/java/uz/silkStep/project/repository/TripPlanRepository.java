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

// This interface extends JpaRepository, which provides basic CRUD operations for the TripPlan entity. 
//Additionally, it includes a method to find all trip plans associated with a specific destination, ordered by their ID in ascending order.