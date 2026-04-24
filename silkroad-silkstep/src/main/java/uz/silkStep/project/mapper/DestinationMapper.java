
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.Destination;
import uz.silkStep.project.dto.request.DestinationRequest;
import uz.silkStep.project.dto.response.DestinationResponse;
import uz.silkStep.project.dto.response.pub.PublicDestinationResponse;

@Mapper(componentModel = "spring")
public interface DestinationMapper extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "status", constant = "INACTIVE")
    Destination toEntity(DestinationRequest request);

    @Mapping(source = "nameUz", target = "nameUz")
    @Mapping(source = "nameEn", target = "nameEn")
    @Mapping(source = "nameRu", target = "nameRu")
    @Mapping(source = "descriptionUz", target = "descriptionUz")
    @Mapping(source = "descriptionEn", target = "descriptionEn")
    @Mapping(source = "descriptionRu", target = "descriptionRu")
    DestinationResponse toResponse(Destination destination);

    PublicDestinationResponse toPubResponse(Destination destination);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void mapToEntity(@MappingTarget Destination destination, DestinationRequest request);
}

// This code defines a MapStruct mapper interface for mapping between Destination entities and their corresponding request and response DTOs.