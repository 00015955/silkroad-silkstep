package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.CommonStatus;

import java.util.UUID;

@Setter
@Getter
public class DestinationFactResponse {
    private UUID id;
    private String icon;
    private String label;
    private String value;
    private CommonStatus status;

    private String labelUz;
    private String labelRu;
    private String labelEn;

    private String valueUz;
    private String valueRu;
    private String valueEn;
}
//  DestinationFactResponse → represents a response DTO for a destination fact, containing fields for the fact's ID, icon, localized label and value, and status. The label and value fields are provided in three languages: Uzbek (Uz), Russian (Ru), and English (En).