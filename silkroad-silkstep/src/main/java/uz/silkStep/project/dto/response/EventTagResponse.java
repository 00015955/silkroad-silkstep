package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.CommonStatus;

import java.util.UUID;

@Setter
@Getter
public class EventTagResponse {
    private UUID id;
    private String tag;
    private CommonStatus status;

    private String tagUz;
    private String tagRu;
    private String tagEn;
}
