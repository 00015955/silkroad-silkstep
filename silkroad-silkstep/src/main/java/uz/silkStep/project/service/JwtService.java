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
/// The JwtService interface defines the contract for handling JSON Web Tokens (JWT) in the application. 
//It includes methods for generating tokens, extracting information from tokens, validating tokens, and managing user sessions through token-based authentication. 
//This service is crucial for securing the application and ensuring that only authenticated users can access protected resources.