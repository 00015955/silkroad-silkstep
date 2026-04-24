package uz.silkStep.project.dto.response;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;

    private String refreshToken;
}
// TokenResponse → represents a response DTO for authentication tokens, containing fields for the access token and refresh token. This class is typically used to return the generated tokens after a successful login or token refresh operation.