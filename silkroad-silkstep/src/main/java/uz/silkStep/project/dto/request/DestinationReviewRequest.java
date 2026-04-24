package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class DestinationReviewRequest {
    private UUID destinationId;
    private BigDecimal rating;
    private String description;
    private String fullName;
    private String email;
}
// DestinationReviewRequest → represents a request DTO for submitting a review for a destination, containing the destination ID, rating, review description, reviewer's full name, and email address.