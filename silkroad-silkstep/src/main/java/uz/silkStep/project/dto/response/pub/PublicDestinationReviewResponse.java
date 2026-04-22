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
