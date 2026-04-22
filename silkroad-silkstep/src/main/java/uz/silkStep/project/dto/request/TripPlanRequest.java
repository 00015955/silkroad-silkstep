package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TripPlanRequest {
    private UUID destinationId;
    private String titleUz;
    private String titleRu;
    private String titleEn;
    private String dayNumber;
    private List<String> activities;
}
