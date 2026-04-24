package uz.silkStep.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.TravelTip;
import uz.silkStep.project.enums.CommonStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface TravelTipRepository extends JpaRepository<TravelTip, UUID> {

    List<TravelTip> findAllByStatusOrderBySortOrderAsc(CommonStatus status);
}

// This interface extends JpaRepository, which provides basic CRUD operations for the TravelTip entity. 
//Additionally, it includes a method to find all travel tips by their status and order them by their sort order in ascending order.