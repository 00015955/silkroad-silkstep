package uz.silkStep.project.service;


import uz.silkStep.project.common.constants.MessageType;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.dto.request.MessageRequest;
import uz.silkStep.project.dto.response.MessageResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.exception.ExceptionType;

public interface MessageService {
    String getMessage(ExceptionType type);

    String getMessage(MessageType type);

    PageableResponse<MessageResponse> findAll(BaseFilter filter);

    void save(MessageRequest request);
}
/// The MessageService interface defines a contract for managing messages within the application, including retrieving messages based on exception types or message types, finding all messages with pagination support, and saving new messages.