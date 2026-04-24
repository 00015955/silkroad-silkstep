package uz.silkStep.project.domain.redis;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("session")
public class Session {
    @Id
    private UUID id;

    @Indexed
    private String username;
    private String userAgent;
    private String ipAddress;
    private LocalDateTime createdAt;
    private Boolean trusted;

    @TimeToLive
    private Long ttl;
}
// Session → represents a user session stored in Redis. 
//It contains fields for the session ID, username, user agent, IP address, creation time, whether the session is trusted, and a time-to-live (TTL) for automatic expiration. 
//The @RedisHash annotation indicates that this class is a Redis hash, and the @Indexed annotation allows for querying by username. The @TimeToLive annotation specifies that the ttl field will be used to determine when the session should expire.