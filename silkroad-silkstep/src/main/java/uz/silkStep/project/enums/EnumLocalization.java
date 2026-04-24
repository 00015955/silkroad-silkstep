package uz.silkStep.project.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnumLocalization implements Serializable {
    String ru;
    String en;
    String uz;
}


// EnumLocalization → represents a localization object that contains translations for three languages: Russian (ru), English (en), and Uzbek (uz). 
//This class is designed to hold localized strings for different languages, allowing the application to support multiple languages and provide appropriate translations based on user preferences or locale settings.