
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.TravelTip;
import uz.silkStep.project.dto.request.TravelTripRequest;
import uz.silkStep.project.dto.response.TravelTripResponse;
import uz.silkStep.project.dto.response.pub.PublicTravelTripResponse;

@Mapper(componentModel = "spring")
public interface TravelTripMapper extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    TravelTip toEntity(TravelTripRequest request);

    TravelTripResponse toResponse(TravelTip travelTip);

    PublicTravelTripResponse toPubResponse(TravelTip travelTip);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void mapToEntity(@MappingTarget TravelTip travelTip, TravelTripRequest request);
}

// This code defines a MapStruct mapper interface for mapping between TravelTip entities and their corresponding request and response DTOs.