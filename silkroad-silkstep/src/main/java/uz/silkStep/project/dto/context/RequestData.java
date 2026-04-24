
package uz.silkStep.project.dto.context;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestData {
    private String accessToken;
    private String ipAddress;
    private String userAgent;
    private UUID sessionId;
}
//RequestData → request context. input data(form)
//This class is a Spring component that holds contextual information about the current HTTP request. 
//It is scoped to the request, meaning a new instance is created for each incoming HTTP request. 
//The class contains fields for storing the access token, IP address, user agent, and session ID associated with the request. 
//This allows other components in the application to access this information throughout the processing of the request without needing to pass it explicitly as method parameters.