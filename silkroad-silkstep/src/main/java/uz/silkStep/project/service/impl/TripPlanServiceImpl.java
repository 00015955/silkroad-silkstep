package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.silkStep.project.domain.Destination;
import uz.silkStep.project.domain.TripPlan;
import uz.silkStep.project.dto.request.TripPlanRequest;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.TripPlanResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.exception.ExceptionType;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.mapper.TripPlanMapper;
import uz.silkStep.project.repository.DestinationRepository;
import uz.silkStep.project.repository.TripPlanRepository;
import uz.silkStep.project.service.TripPlanService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static uz.silkStep.project.exception.ExceptionType.TRIP_PLAN_NOT_FOUND;

/**
 * Created by: Diyora Alieva
 **/

//
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TripPlanServiceImpl implements TripPlanService {

    private final TripPlanRepository tripPlanRepository;
    private final DestinationRepository destinationRepository;
    private final TripPlanMapper tripPlanMapper;

    /**
     * Creates and persists a new {@link TripPlan} entity based on the provided request.
     * <p>
     * This operation is executed within a transactional context to guarantee atomicity.
     * All required fields for the trip plan are expected to be provided in the request object.
     * The method establishes a relationship between the trip plan and its corresponding destination.
     *
     * @param request the {@link TripPlanRequest} containing all necessary data to create a trip plan
     */
    @Override
    @Transactional
    public void create(TripPlanRequest request) {
        Destination destination = destinationRepository.findById(request.getDestinationId()).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        TripPlan tripPlan = new TripPlan();
        tripPlan.setDayNumber(request.getDayNumber());
        tripPlan.setTitleEn(request.getTitleEn());
        tripPlan.setTitleRu(request.getTitleRu());
        tripPlan.setTitleUz(request.getTitleUz());
        tripPlan.setActivities(request.getActivities());
        tripPlan.setDestinationId(destination.getId());
        tripPlanRepository.save(tripPlan);
    }

    /**
     * Updates an existing {@link TripPlan} identified by the given ID.
     * <p>
     * The operation runs within a transactional boundary to ensure data consistency.
     * The provided request is used to overwrite mutable fields of the existing entity.
     * This method assumes that the trip plan already exists and is eligible for modification.
     *
     * @param id the unique identifier of the trip plan to be updated
     * @param request the {@link TripPlanRequest} containing updated values
     */
    @Override
    @Transactional
    public void update(UUID id, TripPlanRequest request) {
        Destination destination = destinationRepository.findById(request.getDestinationId()).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        TripPlan tripPlan = tripPlanRepository.findById(id).orElseThrow(() -> CustomException.of(TRIP_PLAN_NOT_FOUND));
        tripPlan.setDayNumber(request.getDayNumber());
        tripPlan.setTitleEn(request.getTitleEn());
        tripPlan.setTitleRu(request.getTitleRu());
        tripPlan.setTitleUz(request.getTitleUz());
        tripPlan.setActivities(request.getActivities());
        tripPlan.setDestinationId(destination.getId());
        tripPlanRepository.save(tripPlan);
    }

    /**
     * Retrieves a {@link TripPlan} by its unique identifier.
     * <p>
     * This method performs a read operation and converts the entity into a response DTO.
     * It is intended for external exposure where internal entity structure should not be leaked.
     *
     * @param id the unique identifier of the trip plan
     * @return a {@link TripPlanResponse} representing the requested trip plan
     */
    @Override
    public TripPlanResponse findById(UUID id) {
        TripPlan tripPlan = tripPlanRepository.findById(id).orElseThrow(() -> CustomException.of(TRIP_PLAN_NOT_FOUND));
        return tripPlanMapper.toResponse(tripPlan);
    }

    /**
     * Updates the status of a specific {@link TripPlan}.
     * <p>
     * This method is typically used for state transitions such as activation, deactivation,
     * or other lifecycle-related changes. Only the status field is affected by this operation.
     *
     * @param id the unique identifier of the trip plan
     * @param status the new {@link CommonStatus} to be assigned
     */
    @Override
    @Transactional
    public void updateStatus(UUID id, CommonStatus status) {
        tripPlanRepository.findById(id).ifPresentOrElse(tripPlan -> {
            tripPlan.setStatus(status);
            tripPlanRepository.save(tripPlan);
        }, () -> {
            throw new CustomException(TRIP_PLAN_NOT_FOUND);
        });
    }

    /**
     * Retrieves a paginated collection of {@link TripPlan} records.
     * <p>
     * This method supports pagination through the provided {@link BaseFilter} object,
     * allowing efficient handling of large datasets. The result is mapped into response DTOs
     * and wrapped into a standardized pageable response structure.
     *
     * @param filter the {@link BaseFilter} containing pagination parameters such as page and size
     * @return a {@link PageableResponse} containing a page of {@link TripPlanResponse} objects
     */
    @Override
    public PageableResponse<TripPlanResponse> findAll(BaseFilter filter) {
        Page<TripPlanResponse> responsePage = tripPlanRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()))
                .map(tripPlanMapper::toResponse);
        return tripPlanMapper.toPageableResponse(responsePage);
    }


    /**
     * Retrieves all available {@link TripPlan} records without pagination.
     * <p>
     * This method is suitable for cases where the dataset is reasonably small
     * or when full data retrieval is explicitly required.
     *
     * @return a list of {@link TripPlanResponse} representing all trip plans
     */
    @Override
    public List<TripPlanResponse> getAll() {
        return tripPlanRepository.findAll()
                .stream()
                .map(tripPlanMapper::toResponse)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves a list of {@link TripPlan} records associated with a specific destination.
     * <p>
     * The result is ordered based on a predefined sorting strategy (e.g., ascending order),
     * ensuring consistent sequence of trip plan items for the given destination.
     *
     * @param destinationId the unique identifier of the destination
     * @return a list of {@link TripPlanResponse} linked to the specified destination
     */
    @Override
    public List<TripPlanResponse> getListByDestinationId(UUID destinationId) {
        TripPlan tripPlan = tripPlanRepository.findById(destinationId).orElseThrow(() -> CustomException.of(ExceptionType.TRIP_PLAN_NOT_FOUND));
        return tripPlanRepository.findAllByDestinationIdOrderByIdAsc(tripPlan.getId()).stream()
                .map(tripPlanMapper::toResponse)
                .toList();
    }
}

//     * Retrieves a list of {@link TripPlan} records associated with a specific destination.