package uz.silkStep.project.domain;

import jakarta.persistence.*;
import lombok.*;
import uz.silkStep.project.enums.CommonStatus;

import java.time.LocalDate;

/**
 * Created by: Diyora Alieva
 **/

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event extends LocalizedDescriptionNameEntity {

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.ACTIVE;
}
