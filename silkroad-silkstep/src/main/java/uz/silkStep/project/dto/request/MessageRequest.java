package uz.silkStep.project.dto.request;

import lombok.Data;

@Data
public class MessageRequest {
    private String messageKey;
    private String translationUz;
    private String translationRu;
    private String translationEn;
}
// MessageRequest → represents a request DTO for creating or updating a localized message, containing the message key and its translations in Uzbek, Russian, and English.