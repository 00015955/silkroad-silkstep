package uz.silkStep.project.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.silkStep.project.domain.User;
import uz.silkStep.project.dto.context.RequestData;
import uz.silkStep.project.dto.request.LoginRequest;
import uz.silkStep.project.dto.request.RefreshTokenRequest;
import uz.silkStep.project.dto.response.TokenResponse;
import uz.silkStep.project.enums.UserStatus;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.exception.ExceptionType;
import uz.silkStep.project.repository.UserRepository;
import uz.silkStep.project.service.AuthService;
import uz.silkStep.project.service.JwtService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RequestData requestData;

    /**
     * Authenticates a user using provided credentials and generates JWT access and refresh tokens.
     * <p>
     * Marks the user as {@link UserStatus#ACTIVE} upon successful login and persists the change.
     * Delegates token generation to {@link jwtService}, which returns both access and refresh tokens
     * wrapped in a {@link TokenResponse}.
     *
     * @param request the {@link LoginRequest} containing username and password
     * @param response the {@link HttpServletResponse} to potentially write cookies or headers
     * @return a {@link TokenResponse} containing access and refresh tokens
     * @throws CustomException if the user with the given username is not found
     */
    @Override
    public TokenResponse generateToken(LoginRequest request, HttpServletResponse response) {
        User user = userRepository.findByLogin(request.getUsername()).orElseThrow(() -> CustomException.of(ExceptionType.USER_NOT_FOUND));
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);

        return jwtService.generateToken(user.getUsername());
    }

    /**
     * Generates a new access token based on a valid refresh token.
     * <p>
     * Validates the provided refresh token using {@link jwtService#isValidRefreshToken(String)}.
     * If the token is valid, extracts the username from the token and generates a new token pair.
     * Throws an {@link CustomException} with {@link HttpStatus#UNAUTHORIZED} if the refresh token is invalid.
     *
     * @param request the {@link RefreshTokenRequest} containing the refresh token
     * @param response the {@link HttpServletResponse} to potentially write cookies or headers
     * @return a {@link TokenResponse} containing new access and refresh tokens
     * @throws CustomException if the refresh token is invalid
     */
    @Override
    public TokenResponse refreshToken(RefreshTokenRequest request, HttpServletResponse response) {
        boolean validRefreshToken = jwtService.isValidRefreshToken(request.getRefreshToken());
        if (!validRefreshToken) {
            throw new CustomException(ExceptionType.REFRESH_TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
        String username = jwtService.extractUsernameFromRefreshToken(request.getRefreshToken());

        return jwtService.generateToken(username);
    }

    /**
     * Logs out the currently authenticated user by invalidating the access token.
     * <p>
     * Retrieves the access token from {@link requestData} and delegates token invalidation to {@link jwtService#logout(String)}.
     * Ensures that the session associated with the token is removed, effectively terminating authentication.
     */
    @Override
    public void logout() {
        String accessToken = requestData.getAccessToken();
        jwtService.logout(accessToken);
    }
}
