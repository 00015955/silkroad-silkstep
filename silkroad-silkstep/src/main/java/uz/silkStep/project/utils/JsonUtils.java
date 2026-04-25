
package uz.silkStep.project.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micrometer.common.util.StringUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@UtilityClass
public class JsonUtils {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModules(new JavaTimeModule());
    }

    public <T> Optional<T> toObject(String stringValue, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(stringValue)) {
            log.debug("Values is null or empty, class type:{}", typeReference.getType().getTypeName());
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(mapper.readValue(stringValue, typeReference));
        } catch (Exception e) {
            log.error("Deserialization error - value:{}, class:{}", stringValue, typeReference.getType().getTypeName(), e);
        }
        return Optional.empty();
    }

    public String toJson(final Object object) {
        try {
            if (object == null) {
                return null;
            }
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed convert to string", e);
        }
    }

    public <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return mapper.convertValue(fromValue, toValueType);
    }
}
// This utility class provides methods for JSON serialization and deserialization using the Jackson library.