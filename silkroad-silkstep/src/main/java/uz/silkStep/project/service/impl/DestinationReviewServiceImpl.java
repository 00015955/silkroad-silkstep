package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.silkStep.project.domain.Destination;
import uz.silkStep.project.domain.DestinationReview;
import uz.silkStep.project.domain.User;
import uz.silkStep.project.dto.request.DestinationReviewRequest;
import uz.silkStep.project.dto.response.pub.PublicDestinationReviewResponse;
import uz.silkStep.project.enums.Role;
import uz.silkStep.project.enums.UserStatus;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.exception.ExceptionType;
import uz.silkStep.project.repository.DestinationRepository;
import uz.silkStep.project.repository.DestinationReviewRepository;
import uz.silkStep.project.repository.UserRepository;
import uz.silkStep.project.service.DestinationReviewService;
import uz.silkStep.project.utils.SecurityUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DestinationReviewServiceImpl implements DestinationReviewService {

    private final DestinationReviewRepository destinationReviewRepository;
    private final DestinationRepository destinationRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public void create(DestinationReviewRequest request) {
        Destination destination = destinationRepository.findById(request.getDestinationId()).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        DestinationReview destinationReview = new DestinationReview();
        destinationReview.setDestinationId(destination.getId());
        destinationReview.setRating(request.getRating());
        destinationReview.setDescription(request.getDescription());

        User user = new User();
        user.setLogin(request.getEmail());
        user.setFullName(request.getFullName());
        user.setLanguage(SecurityUtils.getCurrentRequestLanguage());
        user.setStatus(UserStatus.REVIEW);
        user.setRole(Role.REVIEW);
        userRepository.saveAndFlush(user);
        destinationReview.setUserId(user.getId());
        destinationReviewRepository.save(destinationReview);
    }

    @Override
    public List<PublicDestinationReviewResponse> getListByDestinationId(UUID destinationId) {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        return destinationReviewRepository.findAllByDestinationIdOrderById(destination.getId()).stream()
                .map(destinationReview -> {
                    PublicDestinationReviewResponse reviewResponse = new PublicDestinationReviewResponse();
                    reviewResponse.setRating(destinationReview.getRating());
                    reviewResponse.setDescription(destinationReview.getDescription());
                    reviewResponse.setEmail(destinationReview.getUser().getLogin());
                    reviewResponse.setFullName(destinationReview.getUser().getFullName());
                    reviewResponse.setCreatedAt(destinationReview.getCreatedAt().toLocalDate());
                    return reviewResponse;
                })
                .toList();
    }

    @Override
    public BigDecimal reportAggregate() {
        return destinationReviewRepository.getAverageRating();
    }
}

// This service implementation provides methods to create new destination reviews, retrieve reviews for a specific destination, and calculate the average rating across all reviews. It handles necessary validations and interactions with the underlying repositories.