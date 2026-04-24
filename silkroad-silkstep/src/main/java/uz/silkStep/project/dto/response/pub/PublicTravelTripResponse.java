package uz.silkStep.project.dto.response.pub;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PublicTravelTripResponse {
    private UUID id;
    private String icon;
    private String title;
    private String subtitle;
    private String description;
}
// PublicTravelTripResponse → represents a response DTO for a public travel trip, containing fields for the trip's ID, icon URL, title, subtitle, and description. This DTO is likely used to provide information about travel trips to the public in a simplified format.