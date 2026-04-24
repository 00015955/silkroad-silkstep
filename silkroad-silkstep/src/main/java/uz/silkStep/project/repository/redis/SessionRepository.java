package uz.silkStep.project.repository.redis;


import org.springframework.data.repository.CrudRepository;
import uz.silkStep.project.domain.redis.Session;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends CrudRepository<Session, UUID> {
    List<Session> findByUsername(String username);
}

// This interface extends CrudRepository, which provides basic CRUD operations for the Session entity. 
//The findByUsername method allows you to retrieve a list of sessions associated with a specific username. 
//The Session entity is expected to have a field named "username" that can be used for this query.