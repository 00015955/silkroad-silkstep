package uz.silkStep.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;


@Configuration
public class HttpClientConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }
}

//HttpClientConfig → external API calls. This configuration class defines a bean for the HttpClient, which is used to make HTTP requests to external APIs. The httpClient method creates a new HttpClient instance with a connection timeout of 10 seconds, ensuring that if an external API does not respond within this time frame, the request will fail gracefully. This HttpClient can be injected into any service or component that needs to perform HTTP operations, providing a centralized and consistent way to manage external API calls throughout the application.