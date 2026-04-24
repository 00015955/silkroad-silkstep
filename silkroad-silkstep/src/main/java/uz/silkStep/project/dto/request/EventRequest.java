package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Setter
@Getter
public class EventRequest extends LocalizedRequest {

    private LocalDate eventDate;

    private String location;

    private Integer sortOrder = 0;

    private String file;

    private String fileName;
}
// EventRequest → represents a request DTO for creating or updating an event, containing the event date, location, sort order, and file information.