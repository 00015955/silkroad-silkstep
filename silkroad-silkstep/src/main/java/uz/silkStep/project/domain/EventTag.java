package uz.silkStep.project.domain;

import jakarta.persistence.*;
import lombok.*;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.utils.SecurityUtils;

import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

@Entity
@Table(name = "event_tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventTag extends AbstractBaseDomain {

    @Column(name = "event_id", nullable = false)
    private UUID eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",insertable = false, updatable = false)
    private Event event;

    @Column(name = "tag_uz", nullable = false, length = 100)
    private String tagUz;

    @Column(name = "tag_ru", nullable = false, length = 100)
    private String tagRu;

    @Column(name = "tag_en", nullable = false, length = 100)
    private String tagEn;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.ACTIVE;

    public String getTag() {
        return switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> tagUz;
            case ru -> tagRu;
            default -> tagEn;
        };
    }
}
// EventTag → represents a tag associated with an event, allowing categorization and filtering of events based on tags.