package uz.silkStep.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.DestinationRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.DestinationResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.DestinationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/destinations")
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody DestinationRequest request) {
        destinationService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody DestinationRequest request) {
        destinationService.update(id, request);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<DestinationResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(destinationService.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(destinationService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam("status") CommonStatus status) {
        destinationService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reorder")
    public ResponseEntity<Void> reorderDestinations(@Valid @RequestBody List<ReorderRequest> requests) {
        destinationService.reorderDestinations(requests);
        return ResponseEntity.ok().build();
    }
}
// DestinationController → manages destination operations. It has five endpoints: