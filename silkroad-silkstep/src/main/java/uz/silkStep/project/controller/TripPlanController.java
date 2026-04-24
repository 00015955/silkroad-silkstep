package uz.silkStep.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.TripPlanRequest;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.TripPlanResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.TripPlanService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/trip-plans")
@RequiredArgsConstructor
public class TripPlanController {

    private final TripPlanService tripPlanService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody TripPlanRequest request) {
        tripPlanService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody TripPlanRequest request) {
        tripPlanService.update(id, request);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<TripPlanResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(tripPlanService.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripPlanResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(tripPlanService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam("status") CommonStatus status) {
        tripPlanService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list-by-destination/{destinationId}")
    public ResponseEntity<List<TripPlanResponse>> getFactById(@PathVariable UUID destinationId) {
        return ResponseEntity.ok(tripPlanService.getListByDestinationId(destinationId));
    }
}
// TripPlanController → manages trip plan operations. It has five endpoints: