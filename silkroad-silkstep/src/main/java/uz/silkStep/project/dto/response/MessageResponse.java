package uz.silkStep.project.dto.response;

import lombok.Data;

@Data
public class MessageResponse {
    private String messageKey;
    private String translationUz;
    private String translationRu;
    private String translationEn;
}
// MessageResponse → represents a response DTO for a message, containing fields for the message key and its translations in three languages: Uzbek (Uz), Russian (Ru), and English (En). This class can be used to return localized messages in API responses.