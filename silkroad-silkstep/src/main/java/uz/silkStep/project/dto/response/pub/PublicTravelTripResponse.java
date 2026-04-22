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
