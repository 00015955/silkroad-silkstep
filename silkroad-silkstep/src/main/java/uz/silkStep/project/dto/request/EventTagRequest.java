package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EventTagRequest {
    private UUID eventId;
    private String tagUz;
    private String tagRu;
    private String tagEn;
}
// EventTagRequest → represents a request DTO for creating or updating an event tag, containing the event ID and localized tag names in Uzbek, Russian, and English.