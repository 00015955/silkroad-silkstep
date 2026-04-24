package uz.silkStep.project.common;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.silkStep.project.domain.User;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    @NotNull
    @Override
    public Optional<User> getCurrentAuditor() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = auth.getPrincipal();

        if (principal instanceof User user) {
            return Optional.of(user);
        }

        return Optional.empty();

    }
}

//SpringSecurityAuditorAware → tracks who changed DB records. It implements AuditorAware<User>, which is used by Spring Data JPA to automatically populate auditing fields (like createdBy and lastModifiedBy) in entities. The getCurrentAuditor method retrieves the currently authenticated user from the SecurityContext. If there is an authenticated user, it returns that user wrapped in an Optional; otherwise, it returns an empty Optional. This allows the application to automatically associate database changes with the user who made them.