
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.EventTag;
import uz.silkStep.project.dto.request.EventTagRequest;
import uz.silkStep.project.dto.response.EventTagResponse;
import uz.silkStep.project.dto.response.pub.PublicEventTagResponse;

@Mapper(componentModel = "spring")
public interface EventTagMapper extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "status", constant = "INACTIVE")
    EventTag toEntity(EventTagRequest request);

    EventTagResponse toResponse(EventTag event);

    PublicEventTagResponse toPubResponse(EventTag event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void mapToEntity(@MappingTarget EventTag event, EventTagRequest request);
}

// This code defines a MapStruct mapper interface for mapping between EventTag entities and their corresponding request and response DTOs.