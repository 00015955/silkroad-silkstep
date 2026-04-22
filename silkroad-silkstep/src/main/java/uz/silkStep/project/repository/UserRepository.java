package uz.silkStep.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.silkStep.project.domain.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    Optional<User> findByLogin(String login);
}
