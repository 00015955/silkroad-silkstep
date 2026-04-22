package uz.silkStep.project.dto.response.pub;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PublicDestinationFactResponse {
    private UUID id;
    private String icon;
    private String label;
    private String value;
}
