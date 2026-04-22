
package uz.silkStep.project.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Language implements Serializable {
    ru,
    uz,
    en;

    public static Language get(String code) {
        if (code == null || code.isEmpty()) {
            return getDefaultLanguage();
        }
        for (Language language : Language.values()) {
            if (code.equals(language.name())) {
                return language;
            }
        }
        return getDefaultLanguage();
    }

    public static Language getDefaultLanguage() {
        return Language.ru;
    }
}
