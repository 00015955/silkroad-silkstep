package uz.silkStep.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final ExceptionType type;
    private final HttpStatus status;

    public CustomException(ExceptionType type) {
        super(type.name());
        this.type = type;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public CustomException(ExceptionType type, HttpStatus status) {
        super(type.name());
        this.type = type;
        this.status = status;
    }

    public static CustomException of(ExceptionType type) {
        return new CustomException(type);
    }
}
