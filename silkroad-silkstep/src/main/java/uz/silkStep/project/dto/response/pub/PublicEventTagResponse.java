package uz.silkStep.project.dto.response.pub;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PublicEventTagResponse {
    private UUID id;
    private String tag;
}
// PublicEventTagResponse → represents a response DTO for a public event tag, containing fields for the tag's ID and the tag name itself. This DTO is likely used to provide information about tags associated with events to the public in a simplified format.