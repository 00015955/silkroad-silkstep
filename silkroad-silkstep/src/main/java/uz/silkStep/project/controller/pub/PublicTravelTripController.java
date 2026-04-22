package uz.silkStep.project.controller.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.silkStep.project.dto.response.pub.PublicTravelTripResponse;
import uz.silkStep.project.service.TravelTripService;

import java.util.List;

@RestController
@RequestMapping("/v1/public/travel-trips")
@RequiredArgsConstructor
public class PublicTravelTripController {

    private final TravelTripService travelTripService;

    @GetMapping
    public ResponseEntity<List<PublicTravelTripResponse>> findAll() {
        return ResponseEntity.ok(travelTripService.getPubAll());
    }
}
