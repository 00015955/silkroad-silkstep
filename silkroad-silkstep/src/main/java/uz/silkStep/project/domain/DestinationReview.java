package uz.silkStep.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

@Entity
@Table(name = "destination_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinationReview extends AbstractBaseDomain {

    @Column(name = "destination_id", nullable = false)
    private UUID destinationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", insertable = false, updatable = false)
    private Destination destination;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "rating", precision = 3, scale = 1)
    private BigDecimal rating;

    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    @Column(name = "description")
    String description;
}
