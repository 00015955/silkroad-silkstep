package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReportDestinationResponse {
    private long count;
    private long monthCount;
    private List<DestinationResponse> popular;
}
// ReportDestinationResponse → represents a response DTO for reporting on destinations, containing fields for the total count of destinations, the count of destinations added in the current month, and a list of popular destinations represented by DestinationResponse objects.