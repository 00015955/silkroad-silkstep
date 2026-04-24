package uz.silkStep.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.MessageRequest;
import uz.silkStep.project.dto.response.MessageResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.MessageService;

@RestController
@RequestMapping("/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<PageableResponse<MessageResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(messageService.findAll(filter));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody MessageRequest request) {
        messageService.save(request);
    }
}
// MessageController → manages message operations. It has two endpoints: