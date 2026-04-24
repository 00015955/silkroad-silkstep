package uz.silkStep.project.dto.response.pub;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PublicDestinationFactResponse {
    private UUID id;
    private String icon;
    private String label;
    private String value;
}
// PublicDestinationFactResponse → represents a response DTO for a public destination fact, containing fields for the fact's ID, icon URL, label, and value. This DTO is likely used to provide specific information or statistics about a destination in a simplified format for public consumption.