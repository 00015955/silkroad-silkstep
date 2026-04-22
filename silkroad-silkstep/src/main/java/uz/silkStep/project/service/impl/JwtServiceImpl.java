package uz.silkStep.project.service.impl;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import uz.silkStep.project.config.ApplicationProperties;
import uz.silkStep.project.domain.redis.Session;
import uz.silkStep.project.dto.context.RequestData;
import uz.silkStep.project.dto.response.TokenResponse;
import uz.silkStep.project.mapper.AuthMapper;
import uz.silkStep.project.repository.redis.SessionRepository;
import uz.silkStep.project.service.JwtService;
import io.jsonwebtoken.Claims;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    public static final String SESSION_ID = "sessionId";
    private final ApplicationProperties applicationProperties;
    private final AuthMapper authMapper;
    private final SessionRepository sessionRepository;
    private final RequestData requestData;

    /**
     * Generates both access and refresh tokens for the given username.
     * <p>
     * This method initiates a new user session, associates it with a unique session identifier,
     * and embeds that session ID into both tokens. The generated tokens are used for
     * authentication (access token) and session renewal (refresh token).
     *
     * @param username the username for which tokens should be generated
     * @return a {@link TokenResponse} containing access and refresh tokens
     */
    @Override
    public TokenResponse generateToken(String username) {
        UUID sessionId = generateSession(username);
        String accessToken = generateAccessToken(username, sessionId);
        String refreshToken = generateRefreshToken(username, sessionId);
        return authMapper.toDto(accessToken, refreshToken);
    }

    /**
     * Extracts the session identifier from the provided access token.
     * <p>
     * This method parses the token, validates its signature, and retrieves
     * the session ID stored as a custom claim.
     *
     * @param token the JWT access token
     * @return the extracted session ID as {@link UUID}
     */
    @Override
    public UUID getSessionIdFromAccessToken(String token) {
        return getSessionId(extractClaims(token, applicationProperties.getAuth().getAccessTokenSecretKey()));
    }

    /**
     * Invalidates the current session associated with the given access token.
     * <p>
     * This method removes the session from persistence, effectively revoking
     * both access and refresh tokens linked to that session.
     *
     * @param accessToken the JWT access token identifying the session
     */
    @Override
    public void logout(String accessToken) {
        UUID sessionId = getSessionId(extractClaims(accessToken, applicationProperties.getAuth().getAccessTokenSecretKey()));
        sessionRepository.deleteById(sessionId);
    }

    /**
     * Validates the provided access token.
     * <p>
     * The validation process includes:
     * <ul>
     *     <li>Verifying token signature</li>
     *     <li>Checking token expiration</li>
     *     <li>Ensuring the associated session exists</li>
     * </ul>
     *
     * @param accessToken the JWT access token
     * @return {@code true} if the token is valid; {@code false} otherwise
     */
    @Override
    public boolean isValidAccessToken(String accessToken) {
        try {
            Claims claims = extractClaims(accessToken, applicationProperties.getAuth().getAccessTokenSecretKey());
            // Token muddati o'tganligini tekshirish
            if (claims.getExpiration().before(new Date())) {
                return false;
            }
            // Session mavjudligini tekshirish
            UUID sessionId = getSessionId(claims);
            return sessionRepository.existsById(sessionId);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extracts session ID from JWT claims.
     *
     * @param claims the JWT claims containing session information
     * @return the session ID as {@link UUID}
     */
    @NotNull
    private static UUID getSessionId(Claims claims) {
        return UUID.fromString(claims.get(SESSION_ID, String.class));
    }

    /**
     * Validates the provided refresh token.
     * <p>
     * Similar to access token validation, this method verifies signature,
     * checks expiration, and ensures the associated session is still active.
     *
     * @param refreshToken the JWT refresh token
     * @return {@code true} if valid; {@code false} otherwise
     */
    @Override
    public boolean isValidRefreshToken(String refreshToken) {
        try {
            Claims claims = extractClaims(refreshToken, applicationProperties.getAuth().getRefreshTokenSecretKey());
            // Token muddati o'tganligini tekshirish
            if (claims.getExpiration().before(new Date())) {
                return false;
            }
            // Session mavjudligini tekshirish
            UUID sessionId = getSessionId(claims);
            return sessionRepository.existsById(sessionId);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Creates and persists a new session for the given user.
     * <p>
     * The session contains metadata such as user agent, IP address,
     * and expiration configuration. The generated session ID is later
     * embedded into JWT tokens.
     *
     * @param username the username associated with the session
     * @return the generated session ID
     */
    @NotNull
    private UUID generateSession(String username) {
        UUID sessionId = UUID.randomUUID();
        Session session = authMapper.toEntity(
                sessionId,
                username,
                requestData.getUserAgent(),
                requestData.getIpAddress(),
                applicationProperties.getAuth().getAccessTokenValiditySeconds(),
                LocalDateTime.now()
        );
        sessionRepository.save(session);
        return session.getId();
    }

    /**
     * Extracts the username (subject) from an access token.
     *
     * @param token the JWT access token
     * @return the username stored in the token
     */
    @Override
    public String extractUsernameFromAccessToken(String token) {
        return extractSubject(token, applicationProperties.getAuth().getAccessTokenSecretKey());
    }

    /**
     * Extracts the username (subject) from a refresh token.
     *
     * @param refreshToken the JWT refresh token
     * @return the username stored in the token
     */
    @Override
    public String extractUsernameFromRefreshToken(String refreshToken) {
        return extractSubject(refreshToken, applicationProperties.getAuth().getRefreshTokenSecretKey());
    }

    /**
     * Generates a signed JWT access token.
     * <p>
     * The token includes subject (username), session ID, issue time,
     * and expiration time, and is signed using the configured secret key.
     *
     * @param username the username (subject)
     * @param sessionId the session identifier
     * @return the generated access token
     */
    public String generateAccessToken(String username, UUID sessionId) {
        return generateToken(
                username,
                sessionId,
                applicationProperties.getAuth().getAccessTokenValiditySeconds(),
                applicationProperties.getAuth().getAccessTokenSecretKey()
        );
    }

    /**
     * Generates a signed JWT refresh token.
     * <p>
     * Similar to access token but typically with a longer expiration time.
     *
     * @param username the username (subject)
     * @param sessionId the session identifier
     * @return the generated refresh token
     */
    public String generateRefreshToken(String username, UUID sessionId) {
        return generateToken(
                username,
                sessionId,
                applicationProperties.getAuth().getRefreshTokenValiditySeconds(),
                applicationProperties.getAuth().getRefreshTokenSecretKey()
        );
    }

    /**
     * Builds and signs a JWT token with the given parameters.
     * <p>
     * This method is a low-level utility responsible for constructing
     * the token payload and applying cryptographic signing.
     *
     * @param username the subject (user identifier)
     * @param sessionId the session ID to embed in the token
     * @param expiration token validity duration in seconds
     * @param secret the secret key used for signing
     * @return the generated JWT token
     */
    private static String generateToken(String username, UUID sessionId, long expiration, String secret) {
        return Jwts.builder()
                .subject(username)
                .claim(SESSION_ID, sessionId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    /**
     * Extracts the subject (username) from a JWT token.
     *
     * @param jwt the JWT token
     * @param secret the secret key used for verification
     * @return the subject (username)
     */
    private static String extractSubject(String jwt, String secret) {
        return extractClaims(jwt, secret)
                .getSubject();
    }

    /**
     * Parses and validates JWT claims using the provided secret key.
     * <p>
     * This method verifies token integrity and extracts payload claims.
     * It throws an exception if the token is invalid or tampered with.
     *
     * @param jwt the JWT token
     * @param secret the secret key used for verification
     * @return the extracted {@link Claims}
     */
    private static Claims extractClaims(String jwt, String secret) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
