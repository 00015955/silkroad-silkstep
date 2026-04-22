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
