package uz.silkStep.project.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum Role implements Serializable {

    ADMIN("ADMIN", new EnumLocalization("Супер Админ", "Super Admin", "Super Admin")),
    USER("USER", new EnumLocalization("Пользователь", "User", "Foydalanuvchi")),
    REVIEW("REVIEW", new EnumLocalization("Обзор", "Review", "Sharh"));

    private final String code;
    private final EnumLocalization name;

    Role(String code, EnumLocalization name) {
        this.code = code;
        this.name = name;
    }

    public static Role getCode(String roleCode) {
        return Stream.of(values()).filter(role -> role.getCode().equals(roleCode)).findFirst().orElse(null);
    }

    public static List<String> getAdminRoles() {
        return List.of(Role.ADMIN.getCode());
    }

    public static Role get(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        return Arrays.stream(values())
                .filter(status -> status.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown Role enum value: " + code));
    }

    @JsonCreator
    public static Role fromJson(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            String code = jsonNode.get("code").asText();
            return get(code);
        } else if (jsonNode.isTextual()) {
            return get(jsonNode.asText());
        }
        throw new IllegalArgumentException("Invalid JSON value for Role");
    }
}
