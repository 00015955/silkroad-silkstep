package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorBaseResponse {
    private String message;
}
// ErrorBaseResponse → represents a base response DTO for error messages, containing a single field for the error message.