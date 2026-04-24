package uz.silkStep.project.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ReorderRequest {

    @NotNull(message = "Id is must be not null")
    private UUID id;

    @NotNull(message = "SortOrder is must be mot null")
    private Integer sortOrder;
}
// ReorderRequest → represents a request DTO for reordering items, containing the ID of the item to be reordered and the new sort order value.