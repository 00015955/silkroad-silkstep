package uz.silkStep.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.utils.SecurityUtils;

import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

@Entity
@Table(name = "destination_fact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinationFact extends AbstractBaseDomain {

    @Column(name = "destination_id", nullable = false)
    private UUID destinationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", insertable = false, updatable = false)
    private Destination destination;

    @Column(name = "icon", length = 100)
    private String icon;

    @Column(name = "label_uz", nullable = false)
    private String labelUz;

    @Column(name = "label_ru", nullable = false)
    private String labelRu;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    @Column(name = "value_uz", nullable = false)
    private String valueUz;

    @Column(name = "value_ru", nullable = false)
    private String valueRu;

    @Column(name = "value_en", nullable = false)
    private String valueEn;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.ACTIVE;

    public String getLabel() {
        return switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> labelUz;
            case ru -> labelRu;
            default -> labelEn;
        };
    }

    public String getValue() {
        return switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> valueUz;
            case ru -> valueRu;
            default -> valueEn;
        };
    }
}
// DestinationFact → represents a factual piece of information about a destination, such as its population, area, or historical significance, with support for multiple languages.