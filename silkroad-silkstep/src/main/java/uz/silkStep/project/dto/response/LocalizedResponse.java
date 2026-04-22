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
