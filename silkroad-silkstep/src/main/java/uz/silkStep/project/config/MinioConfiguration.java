package uz.silkStep.project.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfiguration {
    private final ApplicationProperties applicationProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(applicationProperties.getMinio().getEndpoint())
                .credentials(applicationProperties.getMinio().getAccessKey(), applicationProperties.getMinio().getSecretKey())
                .build();
    }
}
