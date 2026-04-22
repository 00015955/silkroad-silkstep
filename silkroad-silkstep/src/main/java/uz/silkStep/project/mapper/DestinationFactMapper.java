
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.DestinationFact;
import uz.silkStep.project.dto.request.DestinationFactRequest;
import uz.silkStep.project.dto.response.DestinationFactResponse;
import uz.silkStep.project.dto.response.pub.PublicDestinationFactResponse;

@Mapper(componentModel = "spring")
public interface DestinationFactMapper extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    DestinationFact toEntity(DestinationFactRequest request);

    DestinationFactResponse toResponse(DestinationFact destinationFact);

    PublicDestinationFactResponse toPubResponse(DestinationFact destinationFact);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void mapToEntity(@MappingTarget DestinationFact destinationFact, DestinationFactRequest request);
}
