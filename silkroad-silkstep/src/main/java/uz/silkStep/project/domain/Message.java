
package uz.silkStep.project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.silkStep.project.enums.Language;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message extends AbstractBaseDomain {
    private String messageKey;

    @Enumerated(EnumType.STRING)
    private Language language;

    private String translation;
}
// Message → represents a message entity in the application, which can be used for internationalization (i18n) purposes. 
//It contains a message key, the language of the message, and the translation of the message in that language.