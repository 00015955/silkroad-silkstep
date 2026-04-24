package uz.silkStep.project.domain;

import jakarta.persistence.*;
import lombok.*;
import uz.silkStep.project.enums.CommonStatus;

import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

@Entity
@Table(name = "attraction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attraction extends LocalizedDescriptionNameEntity {

    @Column(name = "destination_id", nullable = false)
    private UUID destinationId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", insertable = false, updatable = false)
    private Destination destination;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.ACTIVE;
}
// Attraction → represents an attraction entity that is associated with a destination. 
//It includes properties such as the destination ID, image URL, sort order, and status.