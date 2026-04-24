
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.Booking;
import uz.silkStep.project.dto.request.BookingRequest;
import uz.silkStep.project.dto.response.BookingResponse;

@Mapper(componentModel = "spring")
public interface BookingMapper extends BaseMapper {

    BookingResponse toResponse(Booking booking);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void mapToEntity(@MappingTarget Booking booking, BookingRequest request);
}
// This code defines a MapStruct mapper interface for mapping between Booking entities and their corresponding request and response DTOs.