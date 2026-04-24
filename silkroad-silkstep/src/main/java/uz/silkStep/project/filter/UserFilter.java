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


// UserFilter → represents a filter object for querying users, containing fields for a single UserStatus (status) and a list of UserStatus values (statuses). 
//This filter is used to specify criteria for filtering users based on their status when retrieving user data from the database. 
//The presence of both a single status and a list of statuses allows for flexible querying, enabling the retrieval of users that match either a specific status or any of the statuses in the provided list.