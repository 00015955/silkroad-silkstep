package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.domain.StatItem;
import uz.silkStep.project.enums.CommonStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class DestinationResponse extends LocalizedResponse {
    private UUID id;
    private String slug;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal rating;
    private String recommendedDays;
    private CommonStatus status;
    private Integer sortOrder;
    private List<StatItem> statItems;
}
