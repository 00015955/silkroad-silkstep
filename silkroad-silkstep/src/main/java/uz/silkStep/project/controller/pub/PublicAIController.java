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
