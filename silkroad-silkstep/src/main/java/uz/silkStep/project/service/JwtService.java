package uz.silkStep.project.service;

import uz.silkStep.project.dto.response.TokenResponse;

import java.util.UUID;

public interface JwtService {

    TokenResponse generateToken(String username);

    UUID getSessionIdFromAccessToken(String token);

    void logout(String accessToken);

    boolean isValidAccessToken(String accessToken);

    boolean isValidRefreshToken(String refreshToken);

    String extractUsernameFromAccessToken(String token);

    String extractUsernameFromRefreshToken(String refreshToken);
}
