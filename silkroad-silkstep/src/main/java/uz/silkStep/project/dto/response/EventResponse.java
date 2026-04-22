package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.CommonStatus;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class EventResponse extends LocalizedResponse {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDate eventDate;
    private String location;
    private CommonStatus status;
}
