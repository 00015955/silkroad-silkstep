package uz.silkStep.project.dto.response.pub;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class PublicDestinationReviewResponse {
    private UUID id;
    private BigDecimal rating;
    private String description;
    private String fullName;
    private String email;
    private LocalDate createdAt;
}
// PublicDestinationReviewResponse → represents a response DTO for a public destination review, containing fields for the review's ID, rating, description, reviewer's full name, email, and the date the review was created. This DTO is likely used to provide information about reviews for a destination to the public in a simplified format.