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
// PublicDestinationResponse → represents a response DTO for a public destination, containing fields for the destination's ID, slug (a URL-friendly identifier), name, description, image URL, average rating, and recommended number of days to visit. This DTO is likely used to provide comprehensive information about a destination to the public in a simplified format.