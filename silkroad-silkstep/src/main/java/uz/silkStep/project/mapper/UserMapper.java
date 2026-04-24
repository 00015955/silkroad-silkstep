
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.silkStep.project.domain.User;
import uz.silkStep.project.dto.request.UserRequest;
import uz.silkStep.project.dto.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    User toEntity(UserRequest userRequest);

    @Mapping(source = "createdAt", target = "createdAt")
    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "password", ignore = true)
    void mapToEntity(@MappingTarget User user, UserRequest request);
}

// This code defines a MapStruct mapper interface for mapping between User entities and their corresponding request and response DTOs. It includes methods for converting a UserRequest to a User entity, converting a User entity to a UserResponse, and updating an existing User entity with data from a UserRequest.