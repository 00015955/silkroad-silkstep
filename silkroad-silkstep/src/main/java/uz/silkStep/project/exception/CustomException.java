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

// CustomException → a custom runtime exception class that encapsulates an ExceptionType (an enum representing specific types of exceptions) and an HttpStatus (indicating the HTTP status code associated with the exception). 
//This class provides constructors for creating exceptions based on the type and optionally specifying a custom HTTP status, defaulting to BAD_REQUEST if not provided. 
//It also includes a static factory method for convenience in creating instances of CustomException based on the ExceptionType.