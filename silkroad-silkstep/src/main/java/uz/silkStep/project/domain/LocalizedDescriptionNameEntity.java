package uz.silkStep.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import uz.silkStep.project.utils.SecurityUtils;

import java.io.Serial;

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PROTECTED)
public class LocalizedDescriptionNameEntity extends LocalizedEntity {

    @Serial
    private static final long serialVersionUID = 3345431154L;

    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    @Column(name = "description_en")
    String descriptionEn;

    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    @Column(name = "description_uz")
    String descriptionUz;

    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    @Column(name = "description_ru")
    String descriptionRu;

    public String getDescription() {
        return switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> descriptionUz;
            case ru -> descriptionRu;
            default -> descriptionEn;
        };
    }

    public String getDescriptionNotNullable() {
        String description = switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> descriptionUz;
            case ru -> descriptionRu;
            default -> descriptionEn;
        };
        if (description == null) {
            description = descriptionEn;
        }
        return description;
    }
}
