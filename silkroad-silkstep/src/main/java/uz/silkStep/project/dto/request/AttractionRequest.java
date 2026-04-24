package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Setter
@Getter
public class AttractionRequest extends LocalizedRequest {

    private UUID destinationId;

    private Integer sortOrder = 0;

    private String file;

    private String fileName;
}
//AttractionRequest → represents a request DTO for creating or updating an attraction.