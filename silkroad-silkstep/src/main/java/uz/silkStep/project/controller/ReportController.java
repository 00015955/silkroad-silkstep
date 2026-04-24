package uz.silkStep.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.silkStep.project.dto.response.ReportDestinationResponse;
import uz.silkStep.project.dto.response.ReportEventResponse;
import uz.silkStep.project.service.DestinationReviewService;
import uz.silkStep.project.service.DestinationService;
import uz.silkStep.project.service.EventService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final DestinationReviewService destinationReviewService;
    private final DestinationService destinationService;
    private final EventService eventService;

    @GetMapping("/destination")
    public ResponseEntity<ReportDestinationResponse> getDestinationReport() {
        return ResponseEntity.ok(destinationService.getReport());
    }

    @GetMapping("/event")
    public ResponseEntity<ReportEventResponse> getEventReport() {
        return ResponseEntity.ok(eventService.getReport());
    }

    @GetMapping("/review-aggregate")
    public ResponseEntity<BigDecimal> getReviewAggregate() {
        return ResponseEntity.ok(destinationReviewService.reportAggregate());
    }
}
// ReportController → provides endpoints for generating reports related to destinations and events. It has three endpoints: