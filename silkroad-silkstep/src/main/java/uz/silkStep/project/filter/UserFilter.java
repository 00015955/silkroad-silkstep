package uz.silkStep.project.filter;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.UserStatus;

import java.util.List;

@Getter
@Setter
public class UserFilter extends BaseFilter {
    private UserStatus status;
    private List<UserStatus> statuses;
}
