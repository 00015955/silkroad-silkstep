package uz.silkStep.project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIChatRequest {

    /**
     * User's question
     */
    @NotBlank(message = "Question is required")
    @Size(max = 1000, message = "Question must not exceed 1000 characters")
    private String message;

    /**
     * Optional: if a specific destination is being requested, a slug is provided.
     * For example: "samarkand", "bukhara".
     * If not provided — it will respond about general travel in Uzbekistan.
     */
    private String destinationSlug;

    @Override
    public String toString() {
        return "AIChatRequest{" +
                "message='" + message + '\'' +
                ", destinationSlug='" + destinationSlug + '\'' +
                '}';
    }
}
