package uz.silkStep.project.dto.response.pub;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicAIChatResponse {
    private String message;
    private String destinationSlug;
    private Instant timestamp;
}
