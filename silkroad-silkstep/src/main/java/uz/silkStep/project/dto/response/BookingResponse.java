package uz.silkStep.project.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.silkStep.project.enums.BookingStatus;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class BookingResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UUID guideId;
    private LocalDate tourDate;
    private Integer durationDays;
    private BookingStatus status;
}
// BookingResponse → represents a response DTO for a booking, containing fields for the customer's first name, last name, email, phone number, the ID of the guide associated with the booking, the date of the tour, the duration of the tour in days, and the status of the booking.