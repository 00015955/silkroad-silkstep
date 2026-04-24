package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TravelTripRequest {
    private UUID id;
    private String icon;
    private String titleUz;
    private String titleRu;
    private String titleEn;
    private String subtitleUz;
    private String subtitleRu;
    private String subtitleEn;
    private String descriptionUz;
    private String descriptionRu;
    private String descriptionEn;
    private Integer sortOrder = 0;
}
// TravelTripRequest → represents a request DTO for creating or updating a travel trip, containing fields for the trip's icon, localized titles, subtitles, descriptions in Uzbek, Russian, and English, as well as a sort order for display purposes.