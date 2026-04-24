package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.silkStep.project.domain.Message;
import uz.silkStep.project.dto.response.MessageResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.enums.Language;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper extends BaseMapper {

    PageableResponse<MessageResponse> toPageable(long totalElements, int totalPages, boolean hasNextPage, List<MessageResponse> content);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Message toEntity(String messageKey, Language language, String translation);
}

// This code defines a MapStruct mapper interface for mapping between Message entities and their corresponding response DTOs, as well as a method for creating a pageable response for messages.