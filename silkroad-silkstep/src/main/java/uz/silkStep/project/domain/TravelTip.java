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

/**
 * Created by: Diyora Alieva
 **/

@Entity
@Table(name = "travel_tips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelTip extends AbstractBaseDomain {

    @Column(name = "icon", length = 100)
    private String icon;

    @Column(name = "title_uz", nullable = false)
    private String titleUz;

    @Column(name = "title_ru", nullable = false)
    private String titleRu;

    @Column(name = "title_en", nullable = false)
    private String titleEn;

    @Column(name = "subtitle_uz")
    private String subtitleUz;

    @Column(name = "subtitle_ru")
    private String subtitleRu;

    @Column(name = "subtitle_en")
    private String subtitleEn;

    @Column(name = "description_uz", nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String descriptionUz;

    @Column(name = "description_ru", nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String descriptionRu;

    @Column(name = "description_en", nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String descriptionEn;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

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

    public String getSubtitle() {
        return switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> subtitleUz;
            case ru -> subtitleRu;
            default -> subtitleEn;
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
