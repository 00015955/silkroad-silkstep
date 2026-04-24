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
// PublicEventResponse → represents a response DTO for a public event, containing fields for the event's ID, name, description, image URL, date of the event, and location. This DTO is likely used to provide information about events to the public in a simplified format.