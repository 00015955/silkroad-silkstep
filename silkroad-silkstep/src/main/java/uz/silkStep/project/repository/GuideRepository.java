package uz.silkStep.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.Guide;

import java.util.List;
import java.util.UUID;


@Repository
public interface GuideRepository extends JpaRepository<Guide, UUID> {

    List<Guide> findAllByDestinationId(UUID destinationId);
}

// This interface extends JpaRepository, which provides basic CRUD operations for the Guide entity.