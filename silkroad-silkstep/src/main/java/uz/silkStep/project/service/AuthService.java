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

// This interface defines the contract for an authentication service. 
//It includes methods for generating a token based on user login credentials, refreshing an existing token, and logging out. 
//The methods take in specific request objects and an HTTP response to manage the authentication process effectively.