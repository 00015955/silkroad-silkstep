package uz.silkStep.project.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.silkStep.project.domain.Booking;
import uz.silkStep.project.domain.Destination;
import uz.silkStep.project.dto.request.BookingRequest;
import uz.silkStep.project.dto.request.ConfirmationRequest;
import uz.silkStep.project.dto.response.BookingResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.pub.PublicBookingResponse;
import uz.silkStep.project.dto.response.pub.PublicGuideResponse;
import uz.silkStep.project.enums.BookingStatus;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.mapper.BookingMapper;
import uz.silkStep.project.repository.BookingRepository;
import uz.silkStep.project.repository.DestinationRepository;
import uz.silkStep.project.repository.GuideRepository;
import uz.silkStep.project.service.BookingService;
import uz.silkStep.project.service.MailSenderService;
import uz.silkStep.project.utils.GenerateOtpUtils;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final GuideRepository guideRepository;
    private final DestinationRepository destinationRepository;
    private final MailSenderService mailSenderService;
    private final BookingMapper bookingMapper;

    @Override
    public UUID create(BookingRequest request) {
        Booking booking = new Booking();
        booking.setFirstName(request.getFirstName());
        booking.setLastName(request.getLastName());
        booking.setEmail(request.getEmail());
        booking.setPhone(request.getPhone());
        booking.setGuideId(request.getGuideId());
        booking.setTourDate(request.getTourDate());
        booking.setDurationDays(request.getDurationDays());
        booking.setTotalAmount(request.getTotalAmount());
        booking.setStatus(BookingStatus.PENDING);

        String otpCode = GenerateOtpUtils.generateOtp();
        booking.setOtpCode(otpCode);
        mailSenderService.sendOtp(request.getEmail(), otpCode);
        bookingRepository.save(booking);
        return booking.getId();
    }

    @Override
    public PageableResponse<BookingResponse> findAll(BaseFilter filter) {
        Page<BookingResponse> responsePage = bookingRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()))
                .map(bookingMapper::toResponse);
        return bookingMapper.toPageableResponse(responsePage);
    }

    @Override
    public PublicBookingResponse getInfoForBooking(UUID destinationId) {
        Destination destination = destinationRepository.getReferenceById(destinationId);
        List<PublicGuideResponse> guideResponses = guideRepository.findAllByDestinationId(destinationId)
                .stream()
                .map(guide -> PublicGuideResponse.builder()
                        .name(guide.getName())
                        .description(guide.getDescription())
                        .pricePerDay(guide.getPricePerDay())
                        .rating(guide.getRating())
                        .languages(guide.getLanguages())
                        .build()).toList();
        return PublicBookingResponse.builder()
                .destinationName(destination.getName())
                .guideResponses(guideResponses)
                .build();
    }

    @Override
    public void confirm(ConfirmationRequest confirmationRequest) {
        Booking booking = bookingRepository.getReferenceById(confirmationRequest.getBookingId());
        if (!booking.getOtpCode().equals(confirmationRequest.getOtpCode())) {
            throw new RuntimeException("Could you please check the OTP code again?");
        }
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
    }
}
