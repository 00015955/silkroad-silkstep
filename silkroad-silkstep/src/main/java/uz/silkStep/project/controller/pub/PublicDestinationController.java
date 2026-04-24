package uz.silkStep.project.controller.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.DestinationReviewRequest;
import uz.silkStep.project.dto.response.DestinationStatItemResponse;
import uz.silkStep.project.dto.response.pub.PublicAttractionResponse;
import uz.silkStep.project.dto.response.pub.PublicDestinationFactResponse;
import uz.silkStep.project.dto.response.pub.PublicDestinationResponse;
import uz.silkStep.project.dto.response.pub.PublicDestinationReviewResponse;
import uz.silkStep.project.service.AttractionService;
import uz.silkStep.project.service.DestinationFactService;
import uz.silkStep.project.service.DestinationReviewService;
import uz.silkStep.project.service.DestinationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/public/destinations")
@RequiredArgsConstructor
public class PublicDestinationController {

    private final DestinationService destinationService;
    private final DestinationFactService destinationFactService;
    private final AttractionService attractionService;
    private final DestinationReviewService destinationReviewService;

    @GetMapping
    public ResponseEntity<List<PublicDestinationResponse>> findAll() {
        return ResponseEntity.ok(destinationService.getPublicAll());
    }

    @GetMapping("/stat-items/{destinationId}")
    public ResponseEntity<List<DestinationStatItemResponse>> getStatItemsById(@PathVariable UUID destinationId) {
        return ResponseEntity.ok(destinationService.getStatItems(destinationId));
    }

    @GetMapping("/attractions/{destinationId}")
    public ResponseEntity<List<PublicAttractionResponse>> getAttractionById(@PathVariable UUID destinationId) {
        return ResponseEntity.ok(attractionService.getPublicListByDestinationId(destinationId));
    }

    @GetMapping("/fact/{destinationId}")
    public ResponseEntity<List<PublicDestinationFactResponse>> getFactById(@PathVariable UUID destinationId) {
        return ResponseEntity.ok(destinationFactService.getPubListByDestinationId(destinationId));
    }

    @PostMapping("/review")
    public ResponseEntity<Void> review(@RequestBody DestinationReviewRequest request) {
        destinationReviewService.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/review/{destinationId}")
    public ResponseEntity<List<PublicDestinationReviewResponse>> getReviewById(@PathVariable UUID destinationId) {
        return ResponseEntity.ok(destinationReviewService.getListByDestinationId(destinationId));
    }
}
// PublicDestinationController → manages public destination-related operations. It has several endpoints: