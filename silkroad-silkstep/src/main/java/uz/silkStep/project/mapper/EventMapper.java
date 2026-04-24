
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.Event;
import uz.silkStep.project.dto.request.EventRequest;
import uz.silkStep.project.dto.response.EventResponse;
import uz.silkStep.project.dto.response.pub.PublicEventResponse;

@Mapper(componentModel = "spring")
public interface EventMapper extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "status", constant = "INACTIVE")
    Event toEntity(EventRequest request);

    @Mapping(source = "nameUz", target = "nameUz")
    @Mapping(source = "nameEn", target = "nameEn")
    @Mapping(source = "nameRu", target = "nameRu")
    @Mapping(source = "descriptionUz", target = "descriptionUz")
    @Mapping(source = "descriptionEn", target = "descriptionEn")
    @Mapping(source = "descriptionRu", target = "descriptionRu")
    EventResponse toResponse(Event event);

    PublicEventResponse toPubResponse(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void mapToEntity(@MappingTarget Event event, EventRequest request);
}

// This code defines a MapStruct mapper interface for mapping between Event entities and their corresponding request and response DTOs.