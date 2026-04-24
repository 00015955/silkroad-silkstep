package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalizedRequest {

    private String nameUz;

    private String nameEn;

    private String nameRu;

    private String descriptionUz;

    private String descriptionEn;

    private String descriptionRu;
}
// LocalizedRequest → serves as a base class for request DTOs that require localized fields, containing common properties for names and descriptions in Uzbek, English, and Russian.