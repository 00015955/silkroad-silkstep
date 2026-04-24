package uz.silkStep.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Created by: Tilepbaev Dawletbay Ong`arbay uli
 * Date: 22.04.2026 11:54
 **/

@Setter
@Getter
public class ConfirmationRequest {

    private UUID bookingId;
    private String otpCode;
}
// ConfirmationRequest → represents a request DTO for confirming a booking, containing the booking ID and the OTP code provided by the user for verification.