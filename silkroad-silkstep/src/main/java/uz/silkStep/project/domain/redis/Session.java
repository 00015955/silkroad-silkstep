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
