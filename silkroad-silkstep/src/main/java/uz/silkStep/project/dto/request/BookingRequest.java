package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class BookingRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UUID guideId;
    private LocalDate tourDate;
    private Integer durationDays;
    private BigDecimal totalAmount;
}
