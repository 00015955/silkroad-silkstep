package uz.silkStep.project.dto.response.pub;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class PublicBookingResponse {

    private String destinationName;
    private List<PublicGuideResponse> guideResponses;
}
// PublicBookingResponse → represents a response DTO for public bookings, containing fields for the name of the destination and a list of guide responses (PublicGuideResponse) associated with that destination. This DTO is likely used to provide information about available guides for a specific destination in a booking context.