
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

// Language → represents an enumeration of supported languages in the application, including Russian (ru), Uzbek (uz), and English (en). 
//The enum provides a method to retrieve a language based on a string code, returning a default language (Russian) if the code is null, empty, or does not match any of the defined languages. This enum is likely used to manage language preferences and localization within the application.