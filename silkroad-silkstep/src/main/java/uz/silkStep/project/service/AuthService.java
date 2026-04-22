package uz.silkStep.project.service;

import jakarta.servlet.http.HttpServletResponse;
import uz.silkStep.project.dto.request.LoginRequest;
import uz.silkStep.project.dto.request.RefreshTokenRequest;
import uz.silkStep.project.dto.response.TokenResponse;

public interface AuthService {

    TokenResponse generateToken(LoginRequest request, HttpServletResponse response);

    TokenResponse refreshToken(RefreshTokenRequest request, HttpServletResponse response);

    void logout();
}
