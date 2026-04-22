package uz.silkStep.project.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import uz.silkStep.project.enums.Language;
import uz.silkStep.project.utils.SecurityUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "guides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guide extends AbstractBaseDomain {

    @Column(name = "name_uz", nullable = false)
    private String nameUz;

    @Column(name = "name_ru", nullable = false)
    private String nameRu;

    @Column(name = "name_en", nullable = false)
    private String nameEn;

    @Column(name = "destination_id", nullable = false)
    private UUID destinationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", insertable = false, updatable = false)
    private Destination destination;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerDay;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Language> languages;

    @Column(name = "description_uz", nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String descriptionUz;

    @Column(name = "description_ru", nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String descriptionRu;

    @Column(name = "description_en", nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String descriptionEn;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "include_items", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> includesItems;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "image_url")
    private String imageUrl;

    public String getName() {
        return switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> nameUz;
            case ru -> nameRu;
            default -> nameEn;
        };
    }

    public String getDescription() {
        return switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> descriptionUz;
            case ru -> descriptionRu;
            default -> descriptionEn;
        };
    }
}
