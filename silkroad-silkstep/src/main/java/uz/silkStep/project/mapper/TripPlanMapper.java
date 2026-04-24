
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.TripPlan;
import uz.silkStep.project.dto.request.TripPlanRequest;
import uz.silkStep.project.dto.response.TripPlanResponse;

@Mapper(componentModel = "spring")
public interface TripPlanMapper extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    TripPlan toEntity(TripPlanRequest request);

    TripPlanResponse toResponse(TripPlan tripPlan);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void mapToEntity(@MappingTarget TripPlan tripPlan, TripPlanRequest request);
}

// This code defines a MapStruct mapper interface for mapping between TripPlan entities and their corresponding request and response DTOs.