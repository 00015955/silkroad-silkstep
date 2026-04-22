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
