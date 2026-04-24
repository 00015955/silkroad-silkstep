package uz.silkStep.project.controller.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.BookingRequest;
import uz.silkStep.project.dto.request.ConfirmationRequest;
import uz.silkStep.project.dto.response.pub.PublicBookingResponse;
import uz.silkStep.project.service.BookingService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/public/bookings")
@RequiredArgsConstructor
public class PublicBookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<UUID> save(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.create(request));
    }

    @GetMapping(value = "/booking-info/{destinationId}")
    public ResponseEntity<PublicBookingResponse> getBookingInfo(@PathVariable UUID destinationId) {
        return ResponseEntity.ok(bookingService.getInfoForBooking(destinationId));
    }

    @PostMapping("/confirm")
    public ResponseEntity<Void> confirm(@RequestBody ConfirmationRequest confirmationRequest) {
        bookingService.confirm(confirmationRequest);
        return ResponseEntity.ok().build();
    }
}
// PublicBookingController → manages public booking operations. It has three endpoints: