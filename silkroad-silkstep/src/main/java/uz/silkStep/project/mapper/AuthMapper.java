package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.silkStep.project.domain.redis.Session;
import uz.silkStep.project.dto.response.TokenResponse;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    TokenResponse toDto(String accessToken, String refreshToken);

    @Mapping(target = "trusted", constant = "false")
    @Mapping(target = "id", source = "id")
    Session toEntity(UUID id, String username, String userAgent, String ipAddress, Long ttl, LocalDateTime createdAt);
}
// This code defines a MapStruct mapper interface for mapping between authentication-related data.