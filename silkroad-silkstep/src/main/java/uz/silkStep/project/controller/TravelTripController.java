package uz.silkStep.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.request.TravelTripRequest;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.TravelTripResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.TravelTripService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/travel-trips")
@RequiredArgsConstructor
public class TravelTripController {

    private final TravelTripService travelTripService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody TravelTripRequest request) {
        travelTripService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody TravelTripRequest request) {
        travelTripService.update(id, request);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<TravelTripResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(travelTripService.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelTripResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(travelTripService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam("status") CommonStatus status) {
        travelTripService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reorder")
    public ResponseEntity<Void> reorderDestinations(@Valid @RequestBody List<ReorderRequest> requests) {
        travelTripService.reorder(requests);
        return ResponseEntity.ok().build();
    }
}
// TravelTripController → manages travel trip operations. It has five endpoints: