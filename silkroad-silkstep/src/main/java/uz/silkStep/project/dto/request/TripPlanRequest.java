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
//  TripPlanRequest → represents a request DTO for creating or updating a trip plan, containing fields for the destination ID, localized titles in Uzbek, Russian, and English, the day number of the trip, and a list of activities planned for that day.