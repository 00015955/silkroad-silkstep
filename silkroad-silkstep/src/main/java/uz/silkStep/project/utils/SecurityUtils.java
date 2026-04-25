package uz.silkStep.project.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uz.silkStep.project.common.constants.AuthConstants;
import uz.silkStep.project.domain.User;
import uz.silkStep.project.enums.Language;

import java.util.Collections;
import java.util.Locale;

@UtilityClass
public final class SecurityUtils {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Language getCurrentRequestLanguage() {
        if (getCurrentRequest() != null && StringUtils.isNotEmpty(getCurrentRequest().getHeader(AuthConstants.LANGUAGE))) {
            return Language.valueOf(getCurrentRequest().getHeader(AuthConstants.LANGUAGE));
        }
        return Language.valueOf((Language.en.name()));
    }

    public static Locale getLocale() {
        return Locale.forLanguageTag((Language.en.name()));

    }

    public static HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public static HttpServletRequest getCurrentRequest() {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}

// This utility class provides methods to retrieve the current authenticated user, determine the language of the current request, get the locale, create HTTP headers for JSON content, and access the current HTTP request. 
//It uses Spring Security to access the authentication context and Spring's RequestContextHolder to access the current request attributes.