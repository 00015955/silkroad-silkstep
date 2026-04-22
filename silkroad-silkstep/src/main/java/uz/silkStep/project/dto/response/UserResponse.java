
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
