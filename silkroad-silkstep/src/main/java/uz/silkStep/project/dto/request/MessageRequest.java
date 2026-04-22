package uz.silkStep.project.dto.request;

import lombok.Data;

@Data
public class MessageRequest {
    private String messageKey;
    private String translationUz;
    private String translationRu;
    private String translationEn;
}
