package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.BookingRequest;
import uz.silkStep.project.dto.request.ConfirmationRequest;
import uz.silkStep.project.dto.response.BookingResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.pub.PublicBookingResponse;
import uz.silkStep.project.filter.BaseFilter;

import java.util.UUID;

public interface BookingService {

    UUID create(BookingRequest request);

    PageableResponse<BookingResponse> findAll(BaseFilter filter);

    PublicBookingResponse getInfoForBooking(UUID destinationId);

    void confirm(ConfirmationRequest confirmationRequest);
}
