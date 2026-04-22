package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.CommonStatus;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class TripPlanResponse {
    private UUID id;
    private String title;
    private String dayNumber;
    private CommonStatus status;
    private List<String> activities;

    private String titleUz;
    private String titleEn;
    private String titleRu;
}
