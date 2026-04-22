package uz.silkStep.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.DestinationFactRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.DestinationFactResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.DestinationFactService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/destination-facts")
@RequiredArgsConstructor
public class DestinationFactController {

    private final DestinationFactService destinationFactService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody DestinationFactRequest request) {
        destinationFactService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody DestinationFactRequest request) {
        destinationFactService.update(id, request);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<DestinationFactResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(destinationFactService.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationFactResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(destinationFactService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam("status") CommonStatus status) {
        destinationFactService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reorder")
    public ResponseEntity<Void> reorderDestinations(@Valid @RequestBody List<ReorderRequest> requests) {
        destinationFactService.reorder(requests);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list-by-destination/{destinationId}")
    public ResponseEntity<List<DestinationFactResponse>> getFactById(@PathVariable UUID destinationId) {
        return ResponseEntity.ok(destinationFactService.getListByDestinationId(destinationId));
    }
}
