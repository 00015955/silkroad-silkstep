package uz.silkStep.project.dto.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
// RefreshTokenRequest → represents a request DTO for refreshing an authentication token, containing the refresh token string.
