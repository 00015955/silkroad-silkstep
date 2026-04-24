package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.silkStep.project.common.specifications.UserSpecifications;
import uz.silkStep.project.domain.User;
import uz.silkStep.project.dto.request.UserRequest;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.UserResponse;
import uz.silkStep.project.enums.UserStatus;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.exception.ExceptionType;
import uz.silkStep.project.filter.UserFilter;
import uz.silkStep.project.mapper.UserMapper;
import uz.silkStep.project.repository.UserRepository;
import uz.silkStep.project.service.UserService;
import uz.silkStep.project.utils.RandomUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

// This service implementation manages user-related operations, including creation, retrieval, and updates.
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * Saves a new user.
     * <p>
     * Converts the given {@link UserRequest} into a {@link User} entity.
     * If the password is not provided, a random password is generated.
     * The password is always encoded before saving.
     *
     * @param userRequest the request object containing user data
     */
    @Override
    @Transactional
    public void save(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(Optional.ofNullable(userRequest.getPassword()).orElseGet(RandomUtils::generatePassword)));
        userRepository.save(user);
    }

    /**
     * Updates an existing user by ID.
     * <p>
     * Finds the user by the given ID, maps updated fields from {@link UserRequest},
     * and saves the updated entity.
     *
     * @param id          the unique identifier of the user
     * @param userRequest the request object containing updated user data
     * @throws USER_NOT_FOUND if the user does not exist
     */
    @Override
    @Transactional
    public void edit(UUID id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> CustomException.of(ExceptionType.USER_NOT_FOUND));
        userMapper.mapToEntity(user, userRequest);
        userRepository.save(user);
    }


    /**
     * Retrieves a user by ID.
     *
     * @param id the unique identifier of the user
     * @return the found {@link UserResponse} object
     * @throws USER_NOT_FOUND if the user is not found
     */
    @Override
    public UserResponse findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> CustomException.of(ExceptionType.USER_NOT_FOUND));
        return userMapper.toResponse(user);
    }

    /**
     * Retrieves a paginated list of users based on filter criteria.
     * <p>
     * Applies dynamic filtering using {@link UserSpecifications} and returns
     * the result as a pageable response.
     *
     * @param filter the filter object containing pagination and search criteria
     * @return paginated list of {@link UserResponse} objects
     */
    @Override
    public PageableResponse<UserResponse> findAll(UserFilter filter) {
        filter.setStatuses(List.of(UserStatus.ACTIVE, UserStatus.PENDING, UserStatus.BLOCK));
        Page<UserResponse> responsePage = userRepository.findAll(UserSpecifications.getSpecification(filter), PageRequest.of(filter.getPage(), filter.getSize()))
                .map(userMapper::toResponse);
        return userMapper.toPageableResponse(responsePage);
    }
}

