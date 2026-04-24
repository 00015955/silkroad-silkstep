package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import uz.silkStep.project.domain.TravelTip;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.request.TravelTripRequest;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.TravelTripResponse;
import uz.silkStep.project.dto.response.pub.PublicTravelTripResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.mapper.TravelTripMapper;
import uz.silkStep.project.repository.TravelTipRepository;
import uz.silkStep.project.service.TravelTripService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static uz.silkStep.project.exception.ExceptionType.TRAVEL_TRIP_NOT_FOUND;

/**
 * Created by: Diyora Alieva
 **/

/// This service implementation manages the lifecycle of travel tips, including creation, retrieval,
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TravelTripServiceImpl implements TravelTripService {

    private final TravelTipRepository travelTipRepository;
    private final TravelTripMapper travelTripMapper;

    /**
     * Creates and persists a new {@link TravelTip} entity.
     * <p>
     * This method initializes a new travel tip using the provided request data,
     * including multilingual fields such as titles, subtitles, and descriptions.
     * If the sort order is not explicitly defined, a default value is applied.
     * <p>
     * Executed within a transactional context to ensure atomicity of the operation.
     *
     * @param request the {@link TravelTripRequest} containing travel tip details
     */
    @Override
    @Transactional
    public void create(TravelTripRequest request) {
        TravelTip travelTip = new TravelTip();
        travelTip.setIcon(request.getIcon());
        travelTip.setTitleEn(request.getTitleEn());
        travelTip.setTitleRu(request.getTitleRu());
        travelTip.setTitleUz(request.getTitleUz());
        travelTip.setSubtitleEn(request.getSubtitleEn());
        travelTip.setSubtitleRu(request.getSubtitleRu());
        travelTip.setSubtitleUz(request.getSubtitleUz());
        travelTip.setDescriptionEn(request.getDescriptionEn());
        travelTip.setDescriptionRu(request.getDescriptionRu());
        travelTip.setDescriptionUz(request.getDescriptionUz());
        travelTip.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);

        travelTipRepository.save(travelTip);
    }

    /**
     * Updates an existing {@link TravelTip} identified by the given ID.
     * <p>
     * The method overwrites all updatable fields including icon, multilingual content,
     * and sorting configuration. If no sort order is provided, a default value is assigned.
     * <p>
     * The operation is transactional, ensuring consistency in case of failure.
     *
     * @param id      the unique identifier of the travel tip
     * @param request the {@link TravelTripRequest} containing updated data
     */
    @Override
    @Transactional
    public void update(UUID id, TravelTripRequest request) {
        TravelTip travelTip = travelTipRepository.findById(id).orElseThrow(() -> CustomException.of(TRAVEL_TRIP_NOT_FOUND));
        travelTip.setIcon(request.getIcon());
        travelTip.setTitleEn(request.getTitleEn());
        travelTip.setTitleRu(request.getTitleRu());
        travelTip.setTitleUz(request.getTitleUz());
        travelTip.setSubtitleEn(request.getSubtitleEn());
        travelTip.setSubtitleRu(request.getSubtitleRu());
        travelTip.setSubtitleUz(request.getSubtitleUz());
        travelTip.setDescriptionEn(request.getDescriptionEn());
        travelTip.setDescriptionRu(request.getDescriptionRu());
        travelTip.setDescriptionUz(request.getDescriptionUz());
        travelTip.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);
        travelTipRepository.save(travelTip);
    }


    /**
     * Retrieves a {@link TravelTip} by its unique identifier.
     * <p>
     * This is a read-only operation that converts the entity into a response DTO
     * suitable for external layers such as controllers or APIs.
     *
     * @param id the unique identifier of the travel tip
     * @return a {@link TravelTripResponse} representing the requested travel tip
     */
    @Override
    public TravelTripResponse findById(UUID id) {
        TravelTip travelTip = travelTipRepository.findById(id).orElseThrow(() -> CustomException.of(TRAVEL_TRIP_NOT_FOUND));
        return travelTripMapper.toResponse(travelTip);
    }


    /**
     * Updates the status of a {@link TravelTip}.
     * <p>
     * This method is typically used to manage lifecycle states such as activation,
     * deactivation, or archival. Only the status field is affected.
     *
     * @param id     the unique identifier of the travel tip
     * @param status the new {@link CommonStatus} value
     */
    @Override
    @Transactional
    public void updateStatus(UUID id, CommonStatus status) {
        travelTipRepository.findById(id).ifPresentOrElse(travelTip -> {
            travelTip.setStatus(status);
            travelTipRepository.save(travelTip);
        }, () -> {
            throw new CustomException(TRAVEL_TRIP_NOT_FOUND);
        });
    }

    /**
     * Reorders multiple {@link TravelTip} entities based on the provided list.
     * <p>
     * Each request item contains an identifier and a new sort order value.
     * The method iterates through the collection and updates each entity accordingly.
     * If the input list is empty, the operation is skipped.
     *
     * @param requests list of {@link ReorderRequest} defining new sort orders
     */
    @Override
    @Transactional
    public void reorder(List<ReorderRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) return;
        for (ReorderRequest req : requests) {
            TravelTip travelTip = travelTipRepository.findById(req.getId()).orElseThrow(() -> CustomException.of(TRAVEL_TRIP_NOT_FOUND));
            travelTip.setSortOrder(req.getSortOrder());
            travelTipRepository.save(travelTip);
        }
    }

    /**
     * Retrieves a paginated list of {@link TravelTip} records.
     * <p>
     * This method supports pagination via {@link BaseFilter}, enabling efficient
     * processing of large datasets. Results are mapped into response DTOs and
     * wrapped in a standardized pageable response structure.
     *
     * @param filter the {@link BaseFilter} containing pagination parameters
     * @return a {@link PageableResponse} of {@link TravelTripResponse}
     */
    @Override
    public PageableResponse<TravelTripResponse> findAll(BaseFilter filter) {
        Page<TravelTripResponse> responsePage = travelTipRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()))
                .map(travelTripMapper::toResponse);
        return travelTripMapper.toPageableResponse(responsePage);
    }

    /**
     * Retrieves all {@link TravelTip} records without pagination.
     * <p>
     * Intended for use cases where the dataset size is manageable or
     * full data retrieval is explicitly required.
     *
     * @return a list of {@link TravelTripResponse} representing all travel tips
     */
    @Override
    public List<TravelTripResponse> getAll() {
        return travelTipRepository.findAll()
                .stream()
                .map(travelTripMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicTravelTripResponse> getPubAll() {
        return travelTipRepository.findAllByStatusOrderBySortOrderAsc(CommonStatus.ACTIVE)
                .stream()
                .map(travelTripMapper::toPubResponse)
                .collect(Collectors.toList());
    }
}
