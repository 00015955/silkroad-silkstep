package uz.silkStep.project.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.utils.SecurityUtils;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trip-plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripPlan extends AbstractBaseDomain {

    @Column(name = "destination_id", nullable = false)
    private UUID destinationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", insertable = false, updatable = false)
    private Destination destination;

    @Column(name = "day_number", nullable = false)
    private String dayNumber;

    @Column(name = "title_uz", nullable = false)
    private String titleUz;

    @Column(name = "title_ru", nullable = false)
    private String titleRu;

    @Column(name = "title_en", nullable = false)
    private String titleEn;

    @Column(name = "activities", columnDefinition = "jsonb", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> activities;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.ACTIVE;

    public String getTitle() {
        return switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> titleUz;
            case ru -> titleRu;
            default -> titleEn;
        };
    }
}
// TripPlan → represents a trip plan entity in the application. 
//It contains fields for the destination ID, day number, titles in different languages (Uzbek, Russian, English), a list of activities (stored as JSON), and the status of the trip plan.