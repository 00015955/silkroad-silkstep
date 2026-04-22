
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.Guide;
import uz.silkStep.project.dto.request.GuideRequest;
import uz.silkStep.project.dto.response.GuideResponse;

@Mapper(componentModel = "spring")
public interface GuideMapper extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Guide toEntity(GuideRequest request);

    @Mapping(source = "nameUz", target = "nameUz")
    @Mapping(source = "nameEn", target = "nameEn")
    @Mapping(source = "nameRu", target = "nameRu")
    @Mapping(source = "descriptionUz", target = "descriptionUz")
    @Mapping(source = "descriptionEn", target = "descriptionEn")
    @Mapping(source = "descriptionRu", target = "descriptionRu")
    GuideResponse toResponse(Guide Guide);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void mapToEntity(@MappingTarget Guide Guide, GuideRequest request);
}
