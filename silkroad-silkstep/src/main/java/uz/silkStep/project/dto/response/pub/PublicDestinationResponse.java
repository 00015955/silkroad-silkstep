package uz.silkStep.project.dto.response.pub;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
public class PublicDestinationResponse {
    private UUID id;
    private String slug;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal rating;
    private String recommendedDays;
}
