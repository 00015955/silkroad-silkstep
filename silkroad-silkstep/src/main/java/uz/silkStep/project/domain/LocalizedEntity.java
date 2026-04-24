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
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PROTECTED)
public class LocalizedEntity extends AbstractBaseDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 334543L;

    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    @Column(name = "name_en")
    String nameEn;

    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    @Column(name = "name_uz")
    String nameUz;

    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    @Column(name = "name_ru")
    String nameRu;

    public String getName() {
        return switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> nameUz;
            case en -> nameEn;
            default -> nameRu;
        };
    }

    public String getNameNotNullable() {
        String name = switch (SecurityUtils.getCurrentRequestLanguage()) {
            case uz -> nameUz;
            case en -> nameEn;
            default -> nameRu;
        };
        if (name == null) {
            name = nameRu;
        }
        return name;
    }
}
// LocalizedEntity → represents an entity that has localized names in different languages (English, Uzbek, Russian). 
//It provides methods to retrieve the name based on the current request language.