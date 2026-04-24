package uz.silkStep.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.AttractionRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.AttractionResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.AttractionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody AttractionRequest request) {
        attractionService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody AttractionRequest request) {
        attractionService.update(id, request);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<AttractionResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(attractionService.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(attractionService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam("status") CommonStatus status) {
        attractionService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reorder")
    public ResponseEntity<Void> reorderDestinations(@Valid @RequestBody List<ReorderRequest> requests) {
        attractionService.reorderAttraction(requests);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list-by-destination/{destinationId}")
    public ResponseEntity<List<AttractionResponse>> getAttractionById(@PathVariable UUID destinationId) {
        return ResponseEntity.ok(attractionService.getListByDestinationId(destinationId));
    }
}
// AttractionController → manages attraction-related operations. It has several endpoints: