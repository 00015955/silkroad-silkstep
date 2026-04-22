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
