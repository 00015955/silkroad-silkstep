package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.CommonStatus;

import java.util.UUID;

@Setter
@Getter
public class EventTagResponse {
    private UUID id;
    private String tag;
    private CommonStatus status;

    private String tagUz;
    private String tagRu;
    private String tagEn;
}
// EventTagResponse → represents a response DTO for an event tag, containing fields for the tag's ID, localized tag name, and status. The tag field is provided in three languages: Uzbek (Uz), Russian (Ru), and English (En).