package uz.silkStep.project.repository.redis;


import org.springframework.data.repository.CrudRepository;
import uz.silkStep.project.domain.redis.Session;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends CrudRepository<Session, UUID> {
    List<Session> findByUsername(String username);
}
