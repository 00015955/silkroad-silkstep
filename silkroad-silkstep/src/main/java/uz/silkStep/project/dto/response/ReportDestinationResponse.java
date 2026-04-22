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
