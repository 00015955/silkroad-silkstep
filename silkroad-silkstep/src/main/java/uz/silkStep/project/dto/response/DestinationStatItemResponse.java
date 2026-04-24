package uz.silkStep.project.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DestinationStatItemResponse {
    private String value;
    private String label;
}
// DestinationStatItemResponse → represents a response DTO for a destination statistic item, containing fields for the value and label of the statistic.