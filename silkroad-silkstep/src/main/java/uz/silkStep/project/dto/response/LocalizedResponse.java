package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalizedResponse {

    private String nameUz;
    private String nameEn;
    private String nameRu;

    private String descriptionUz;
    private String descriptionEn;
    private String descriptionRu;
}
// LocalizedResponse → represents a base response DTO for localized content, containing fields for the name and description in three languages: Uzbek (Uz), English (En), and Russian (Ru). This class can be extended by other response DTOs that require localized fields.