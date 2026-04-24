
package uz.silkStep.project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private Auth auth;
    private Minio minio;
    private Gemini gemini;

    @Data
    public static class Auth {
        private String accessTokenSecretKey;
        private String refreshTokenSecretKey;
        private long accessTokenValiditySeconds;
        private long refreshTokenValiditySeconds;
    }

    @Data
    public static class Minio {
        private String endpoint;
        private String accessKey;
        private String secretKey;
        private Map<Bucket, String> bucketNames;

        public enum Bucket {
            ATTACHMENTS,
        }
    }

    @Data
    public static class Gemini {
        private String key;
        private String url;
    }
}

//ApplicationProperties → config (JWT, MinIO, AI). This class is a configuration properties holder for the application. It uses Spring Boot's @ConfigurationProperties to bind properties from the application's configuration files (like application.yml or application.properties) to Java objects. The class contains nested static classes for different configuration sections: Auth for JWT token settings, Minio for MinIO storage settings, and Gemini for AI-related settings. Each nested class has fields corresponding to specific configuration properties, allowing for easy access to these properties throughout the application.