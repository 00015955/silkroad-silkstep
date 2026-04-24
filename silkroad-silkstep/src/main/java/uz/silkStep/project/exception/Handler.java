package uz.silkStep.project.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.silkStep.project.dto.response.ErrorBaseResponse;
import uz.silkStep.project.service.MessageService;

@RestControllerAdvice
public class Handler {

    private final MessageService messageService;

    public Handler(MessageService messageService) {
        this.messageService = messageService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorBaseResponse> handleException(Exception e) {
        ErrorBaseResponse response = new ErrorBaseResponse();
        response.setMessage(e.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorBaseResponse> handleException(CustomException e) {
        ErrorBaseResponse response = new ErrorBaseResponse();
        response.setMessage(messageService.getMessage(e.getType()));
        return ResponseEntity.status(e.getStatus()).body(response);
    }
}

// Handler → a global exception handler for the application. 
//It uses @RestControllerAdvice to intercept exceptions thrown by controllers and handle them in a centralized manner.