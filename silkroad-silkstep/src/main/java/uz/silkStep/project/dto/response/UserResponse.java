
package uz.silkStep.project.dto.response;

import lombok.Data;
import uz.silkStep.project.enums.Role;
import uz.silkStep.project.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String login;
    private String phone;
    private String fullName;
    private Role role;
    private UserStatus status;
    private LocalDateTime createdAt;
}
// UserResponse → represents a response DTO for user information, containing fields for the user's ID, login, phone number, full name, role, status, and the timestamp of when the user was created. This class is typically used to return user details in API responses.