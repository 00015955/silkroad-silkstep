package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    private String username;

    private String password;
}
// LoginRequest → represents a request DTO for user login, containing the username and password fields.