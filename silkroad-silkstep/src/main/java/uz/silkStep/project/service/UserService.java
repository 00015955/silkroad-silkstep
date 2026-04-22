package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.UserRequest;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.UserResponse;
import uz.silkStep.project.filter.UserFilter;

import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

public interface UserService {

    void save(UserRequest userRequest);

    void edit(UUID id, UserRequest userRequest);

    UserResponse findById(UUID id);

    PageableResponse<UserResponse> findAll(UserFilter filter);
}
