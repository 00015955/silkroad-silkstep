package uz.silkStep.project.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.domain.StatItem;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class DestinationRequest extends LocalizedRequest {

    @NotBlank(message = "Slug is required")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "The slug must contain only lowercase letters, numbers, and hyphens")
    private String slug;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private BigDecimal rating;

    private String recommendedDays;

    private Integer sortOrder = 0;
    private String file;
    private String fileName;
    private List<StatItem> statItems;
}
// DestinationRequest → represents a request DTO for creating or updating a destination, containing the slug, rating, recommended days, sort order, file information, and a list of statistical items related to the destination.