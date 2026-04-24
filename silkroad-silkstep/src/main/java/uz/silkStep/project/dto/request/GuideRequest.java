package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.Language;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GuideRequest {
    private UUID id;
    private UUID destinationId;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private String descriptionUz;
    private String descriptionRu;
    private String descriptionEn;
    private BigDecimal pricePerDay;
    private BigDecimal rating;
    private List<String> includesItems;
    private Boolean available;
    private String file;
    private String fileName;
    private List<Language> languages;
}
// GuideRequest → represents a request DTO for creating or updating a guide, containing the guide's ID, associated destination ID, localized names and descriptions in Uzbek, Russian, and English, price per day, rating, list of included items, availability status, file information for the guide's profile picture or document, and the languages spoken by the guide.