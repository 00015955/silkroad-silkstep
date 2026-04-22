
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
