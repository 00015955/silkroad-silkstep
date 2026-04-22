package uz.silkStep.project.dto.response.pub;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class PublicEventResponse {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDate eventDate;
    private String location;
}
