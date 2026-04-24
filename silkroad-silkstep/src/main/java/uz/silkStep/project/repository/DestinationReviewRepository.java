package uz.silkStep.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.DestinationReview;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Repository
public interface DestinationReviewRepository extends JpaRepository<DestinationReview, UUID> {

    List<DestinationReview> findAllByDestinationIdOrderById(UUID destinationId);

    @Query("SELECT COALESCE(AVG(r.rating), 0) FROM DestinationReview r")
    BigDecimal getAverageRating();
}

// This interface extends JpaRepository, which provides basic CRUD operations for the DestinationReview entity. 
//It also includes a custom query to calculate the average rating of all reviews.