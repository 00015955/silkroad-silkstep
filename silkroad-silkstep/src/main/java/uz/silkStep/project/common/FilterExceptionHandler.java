package uz.silkStep.project.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import uz.silkStep.project.dto.response.ErrorBaseResponse;
import uz.silkStep.project.utils.JsonUtils;

import java.io.IOException;

@Component
public class FilterExceptionHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ErrorBaseResponse errorResponse = new ErrorBaseResponse();
        errorResponse.setMessage(authException.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JsonUtils.toJson(errorResponse));
    }
}

//FilterExceptionHandler → handles auth errors (401) by sending a JSON response with the error message. It implements AuthenticationEntryPoint, which is called when an unauthenticated user tries to access a protected resource. The commence method creates an ErrorBaseResponse, sets the HTTP status to 401, and writes the error message as JSON to the response body.