package uz.silkStep.project.dto.response;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;

    private String refreshToken;
}
