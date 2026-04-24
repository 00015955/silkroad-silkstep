package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReportEventResponse {
    private long count;
    private long monthCount;
    private List<EventResponse> popular;
}
// ReportEventResponse → represents a response DTO for reporting on events, containing fields for the total count of events, the count of events added in the current month, and a list of popular events represented by EventResponse objects.