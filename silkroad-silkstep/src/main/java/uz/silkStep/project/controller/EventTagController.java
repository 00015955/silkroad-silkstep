package uz.silkStep.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.EventTagRequest;
import uz.silkStep.project.dto.response.EventTagResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.EventTagService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/event-tags")
@RequiredArgsConstructor
public class EventTagController {

    private final EventTagService eventTagService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody EventTagRequest request) {
        eventTagService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody EventTagRequest request) {
        eventTagService.update(id, request);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<EventTagResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(eventTagService.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventTagResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventTagService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam("status") CommonStatus status) {
        eventTagService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }
}
// EventTagController → manages event tag operations. It has five endpoints: