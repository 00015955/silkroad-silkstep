package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.Language;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class GuideResponse {
    private UUID id;
    private UUID destinationId;
    private String name;
    private String description;
    private BigDecimal pricePerDay;
    private BigDecimal rating;
    private Boolean available;
    private String imageUrl;
    private List<Language> languages;
    private List<String> includesItems;

    private String nameUz;
    private String nameRu;
    private String nameEn;
    private String descriptionUz;
    private String descriptionRu;
    private String descriptionEn;
}
