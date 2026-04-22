package uz.silkStep.project.dto.response;

import lombok.Data;

@Data
public class MessageResponse {
    private String messageKey;
    private String translationUz;
    private String translationRu;
    private String translationEn;
}
