package uz.silkStep.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import uz.silkStep.project.enums.CommonStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by: Diyora Alieva
 **/

@Entity
@Table(name = "destination")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Destination extends LocalizedDescriptionNameEntity {

    @Column(name = "slug", nullable = false, unique = true, length = 100)
    private String slug;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

//    @Column(name = "rating", precision = 3, scale = 1)
//    private BigDecimal rating;

    @Column(name = "recommended_days", length = 50)
    private String recommendedDays;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.ACTIVE;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<Attraction> attractions = new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DestinationReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DestinationFact> facts = new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripPlan> tripPlans = new ArrayList<>();

    @Column(name = "stats", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<StatItem> statItems;

    public BigDecimal getRating() {
        if (reviews == null || reviews.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = reviews.stream()
                .map(DestinationReview::getRating)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long count = reviews.stream()
                .map(DestinationReview::getRating)
                .filter(Objects::nonNull)
                .count();

        if (count == 0) {
            return BigDecimal.ZERO;
        }

        return sum.divide(
                BigDecimal.valueOf(count),
                2,
                RoundingMode.HALF_UP
        );
    }
}
// Destination → represents a travel destination, including properties such as slug, image URL, recommended days to visit, status, sort order, and relationships to attractions, reviews, facts, and trip plans. 
//It also includes a method to calculate the average rating based on associated reviews.