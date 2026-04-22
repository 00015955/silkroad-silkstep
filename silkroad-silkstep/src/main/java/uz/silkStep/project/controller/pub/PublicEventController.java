package uz.silkStep.project.controller.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.silkStep.project.dto.response.pub.PublicEventResponse;
import uz.silkStep.project.dto.response.pub.PublicEventTagResponse;
import uz.silkStep.project.service.EventService;
import uz.silkStep.project.service.EventTagService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/public/events")
@RequiredArgsConstructor
public class PublicEventController {

    private final EventService eventService;
    private final EventTagService eventTagService;

    @GetMapping
    public ResponseEntity<List<PublicEventResponse>> findAll() {
        return ResponseEntity.ok(eventService.getPubAll());
    }

    @GetMapping("/tag/{eventId}")
    public ResponseEntity<List<PublicEventTagResponse>> getTagById(@PathVariable UUID eventId) {
        return ResponseEntity.ok(eventTagService.getPubListByEventId(eventId));
    }
}
