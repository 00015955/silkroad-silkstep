package uz.silkStep.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.EventRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.EventResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody EventRequest request) {
        eventService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody EventRequest request) {
        eventService.update(id, request);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<EventResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(eventService.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam("status") CommonStatus status) {
        eventService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reorder")
    public ResponseEntity<Void> reorderDestinations(@Valid @RequestBody List<ReorderRequest> requests) {
        eventService.reorder(requests);
        return ResponseEntity.ok().build();
    }
}
// EventController → manages event operations. It has five endpoints: