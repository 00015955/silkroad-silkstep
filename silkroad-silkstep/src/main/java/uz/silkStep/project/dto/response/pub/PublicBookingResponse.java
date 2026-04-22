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
