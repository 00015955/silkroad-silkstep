
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.Attraction;
import uz.silkStep.project.dto.request.AttractionRequest;
import uz.silkStep.project.dto.response.AttractionResponse;
import uz.silkStep.project.dto.response.pub.PublicAttractionResponse;

@Mapper(componentModel = "spring")
public interface AttractionMapper extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "status", constant = "INACTIVE")
    Attraction toEntity(AttractionRequest request);

    @Mapping(source = "nameUz", target = "nameUz")
    @Mapping(source = "nameEn", target = "nameEn")
    @Mapping(source = "nameRu", target = "nameRu")
    @Mapping(source = "descriptionUz", target = "descriptionUz")
    @Mapping(source = "descriptionEn", target = "descriptionEn")
    @Mapping(source = "descriptionRu", target = "descriptionRu")
    AttractionResponse toResponse(Attraction attraction);


    PublicAttractionResponse toPubResponse(Attraction attraction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void mapToEntity(@MappingTarget Attraction attraction, AttractionRequest request);
}
