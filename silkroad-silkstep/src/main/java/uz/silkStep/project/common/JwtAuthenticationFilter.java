package uz.silkStep.project.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.silkStep.project.dto.context.RequestData;
import uz.silkStep.project.service.JwtService;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RequestData requestData;

    public JwtAuthenticationFilter(JwtService jwtService, @Lazy UserDetailsService userDetailsService, RequestData requestData) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.requestData = requestData;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        setRequestData(request);

        // Skip if the Authorization header is missing or does not start with Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the token after "Bearer "
        jwt = authHeader.substring(7);
        log.info("{} token {}", request.getRequestURI(), jwt);
        try {
            username = jwtService.extractUsernameFromAccessToken(jwt);
            // If a username exists and authentication has not been set
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // If the token is valid, set up authentication
                if (jwtService.isValidAccessToken(jwt)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    requestData.setAccessToken(jwt);
                    requestData.setSessionId(jwtService.getSessionIdFromAccessToken(jwt));
                }
            }
        } catch (Exception e) {
            // If the token is invalid, continue without authentication
            logger.warn("Error while verifying JWT token " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private void setRequestData(HttpServletRequest request) {
        requestData.setIpAddress(getClientIp(request));
        requestData.setUserAgent(request.getHeader("User-Agent"));
    }

    public static String getClientIp(HttpServletRequest request) {
        String ipAddress = null;
        String[] headerNames = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED",
                "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_CLIENT_IP",
                "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED",
                "HTTP_VIA",
                "REMOTE_ADDR"
        };

        for (String header : headerNames) {
            ipAddress = request.getHeader(header);
            if (ipAddress != null && !ipAddress.isEmpty() && !"unknown".equalsIgnoreCase(ipAddress)) {
                break;
            }
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // X-Forwarded-For may contain multiple IPs: client, proxy1, proxy2
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }
        return ipAddress;
    }
}
