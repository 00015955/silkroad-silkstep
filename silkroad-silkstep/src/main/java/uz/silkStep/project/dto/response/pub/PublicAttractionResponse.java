package uz.silkStep.project.dto.response.pub;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PublicAttractionResponse {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
}
// PublicAttractionResponse → represents a response DTO for a public attraction, containing fields for the attraction's ID, name, description, and image URL. This DTO is likely used to provide information about attractions to the public in a simplified format.