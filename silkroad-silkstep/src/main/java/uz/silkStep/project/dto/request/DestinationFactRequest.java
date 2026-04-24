package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DestinationFactRequest {
    private UUID destinationId;
    private String icon;
    private String labelUz;
    private String labelRu;
    private String labelEn;
    private String valueUz;
    private String valueRu;
    private String valueEn;
    private Integer sortOrder = 0;
}
// DestinationFactRequest → represents a request DTO for creating or updating a destination fact, containing the destination ID, icon, localized labels and values in Uzbek, Russian, and English, as well as the sort order.