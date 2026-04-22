
package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.Role;

@Setter
@Getter
public class UserRequest {

    private String login;

    private String phone;

    private String fullName;

    private String password;

    private Role role;
}
