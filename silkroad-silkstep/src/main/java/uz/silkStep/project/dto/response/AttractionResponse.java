package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.CommonStatus;

import java.util.UUID;

@Setter
@Getter
public class AttractionResponse extends LocalizedResponse {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private Integer sortOrder;
    private CommonStatus status;
}
// AttractionResponse → represents a response DTO for an attraction, containing fields for the attraction's ID, localized name and description, image URL, sort order, and status.