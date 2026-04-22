package uz.silkStep.project.common.specifications;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import uz.silkStep.project.domain.User;
import uz.silkStep.project.enums.UserStatus;
import uz.silkStep.project.filter.UserFilter;

import java.util.List;

@UtilityClass
public class UserSpecifications {

    private static Specification<User> hasStatus(UserStatus status, List<UserStatus> statuses) {
        if (status == null && (statuses == null || statuses.isEmpty())) {
            return null;
        }

        return (root, query, cb) -> {
            if (status != null) {
                return cb.equal(root.get("status"), status);
            }
            return root.get("status").in(statuses);
        };
    }

    private static Specification<User> searchByKey(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("fullName")), "%" + name.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("login")), "%" + name.toLowerCase() + "%"),
                cb.like(root.get("phone"), "%" + name + "%")
        );
    }

    public static Specification<User> getSpecification(UserFilter filter) {
        return Specification
                .allOf(hasStatus(filter.getStatus(), filter.getStatuses()))
                .and(searchByKey(filter.getSearch()));
    }
}
