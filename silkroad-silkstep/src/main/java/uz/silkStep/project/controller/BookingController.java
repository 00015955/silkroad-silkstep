package uz.silkStep.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.silkStep.project.dto.response.BookingResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.BookingService;

@RestController
@RequestMapping("/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<PageableResponse<BookingResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(bookingService.findAll(filter));
    }
}
// BookingController → handles booking-related operations. 
//It has a single endpoint /v1/booking that accepts GET requests and returns a pageable response containing BookingResponse objects wrapped in a ResponseEntity. 
//The controller delegates the retrieval of booking data to the BookingService, which processes the provided BaseFilter to fetch the relevant bookings.