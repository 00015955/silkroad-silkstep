package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.CommonStatus;

import java.util.UUID;

@Setter
@Getter
public class TravelTripResponse {
    private UUID id;
    private String icon;
    private String title;
    private String subtitle;
    private String description;
    private CommonStatus status;

    private String titleUz;
    private String titleEn;
    private String titleRu;
    private String subTitleUz;
    private String subTitleEn;
    private String subTitleRu;
    private String descriptionUz;
    private String descriptionEn;
    private String descriptionRu;
}
