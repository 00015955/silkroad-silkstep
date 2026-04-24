package uz.silkStep.project.controller.pub;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.silkStep.project.dto.request.AIChatRequest;
import uz.silkStep.project.dto.response.pub.PublicAIChatResponse;
import uz.silkStep.project.service.AIService;

@RestController
@RequestMapping("/v1/public/ai")
@RequiredArgsConstructor
public class PublicAIController {

    private final AIService aiService;

    @PostMapping(value = "/chat")
    public ResponseEntity<PublicAIChatResponse> chat(@Valid @RequestBody AIChatRequest request) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(aiService.chat(request));
    }
}

//PublicAIController → handles AI chat requests. It has a single endpoint /v1/public/ai/chat that accepts POST requests with an AIChatRequest body. The controller delegates the chat processing to the AIService and returns a PublicAIChatResponse wrapped in a ResponseEntity with JSON content type.