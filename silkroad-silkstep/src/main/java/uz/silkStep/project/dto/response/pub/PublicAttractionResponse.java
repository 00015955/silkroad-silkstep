package uz.silkStep.project.dto.response.pub;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PublicAttractionResponse {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
}
