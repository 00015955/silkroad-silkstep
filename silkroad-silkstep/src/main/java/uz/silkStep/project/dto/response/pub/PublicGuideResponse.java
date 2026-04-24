package uz.silkStep.project.dto.response.pub;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.Language;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by: Tilepbaev Dawletbay Ong`arbay uli
 * Date: 22.04.2026 10:22
 **/

@Getter
@Setter
@Builder
public class PublicGuideResponse {

    private String name;
    private String description;
    private BigDecimal rating;
    private List<Language> languages;
    private BigDecimal pricePerDay;
}
// PublicGuideResponse → represents a response DTO for a public guide, containing fields for the guide's name, description, average rating, list of languages spoken, and price per day. This DTO is likely used to provide information about guides available for booking to the public in a simplified format.