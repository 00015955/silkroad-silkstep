package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import uz.silkStep.project.domain.Attraction;
import uz.silkStep.project.domain.Destination;
import uz.silkStep.project.dto.request.AttractionRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.AttractionResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.pub.PublicAttractionResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.exception.ExceptionType;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.mapper.AttractionMapper;
import uz.silkStep.project.repository.AttractionRepository;
import uz.silkStep.project.repository.DestinationRepository;
import uz.silkStep.project.service.AttractionService;
import uz.silkStep.project.service.FileService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static uz.silkStep.project.exception.ExceptionType.ATTRACTION_NOT_FOUND;

/**
 * Created by: Diyora Alieva
 **/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final DestinationRepository destinationRepository;
    private final AttractionRepository attractionRepository;
    private final AttractionMapper attractionMapper;
    private final FileService fileService;

    /**
     * Creates a new {@link Attraction} associated with a {@link Destination}.
     * <p>
     * Fetches the target {@link Destination} by ID to ensure it exists.
     * Sets all localized names and descriptions, sort order, and optionally uploads an image using {@link fileService}.
     * Persists the new Attraction in the {@link attractionRepository}.
     *
     * @param request the {@link AttractionRequest} containing all details for creation
     * @throws CustomException if the {@link Destination} is not found
     */
    @Override
    @Transactional
    public void create(AttractionRequest request) {
        Destination destination = destinationRepository.findById(request.getDestinationId()).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        Attraction attraction = new Attraction();
        attraction.setNameEn(request.getNameEn());
        attraction.setNameUz(request.getNameUz());
        attraction.setNameRu(request.getNameRu());
        attraction.setDescriptionEn(request.getDescriptionEn());
        attraction.setDescriptionRu(request.getDescriptionRu());
        attraction.setDescriptionUz(request.getDescriptionUz());
        attraction.setDestinationId(destination.getId());
        attraction.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);
        if (StringUtils.isNoneBlank(request.getFile())) {
            attraction.setImageUrl(fileService.saveAttachment(request.getFileName(), request.getFile()));
        }
        attractionRepository.save(attraction);
    }

    /**
     * Updates an existing {@link Attraction} by ID.
     * <p>
     * Validates existence of both the target {@link Destination} and {@link Attraction}.
     * Updates all localized fields, sort order, and optionally replaces the image.
     *
     * @param id      the UUID of the {@link Attraction} to update
     * @param request the {@link AttractionRequest} containing updated details
     * @throws CustomException if the {@link Destination} or {@link Attraction} is not found
     */
    @Override
    @Transactional
    public void update(UUID id, AttractionRequest request) {
        Destination destination = destinationRepository.findById(request.getDestinationId()).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        Attraction attraction = attractionRepository.findById(id).orElseThrow(() -> CustomException.of(ATTRACTION_NOT_FOUND));
        attraction.setNameEn(request.getNameEn());
        attraction.setNameUz(request.getNameUz());
        attraction.setNameRu(request.getNameRu());
        attraction.setDescriptionEn(request.getDescriptionEn());
        attraction.setDescriptionRu(request.getDescriptionRu());
        attraction.setDescriptionUz(request.getDescriptionUz());
        attraction.setDestinationId(destination.getId());
        attraction.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);
        if (StringUtils.isNoneBlank(request.getFile())) {
            attraction.setImageUrl(fileService.saveAttachment(request.getFileName(), request.getFile()));
        }
        attractionRepository.save(attraction);
    }

    /**
     * Retrieves a single {@link Attraction} by its ID.
     *
     * @param id the UUID of the {@link Attraction} to fetch
     * @return the mapped {@link AttractionResponse}
     * @throws CustomException if the {@link Attraction} is not found
     */
    @Override
    public AttractionResponse findById(UUID id) {
        Attraction attraction = attractionRepository.findById(id).orElseThrow(() -> CustomException.of(ATTRACTION_NOT_FOUND));
        return attractionMapper.toResponse(attraction);
    }

    /**
     * Updates the status of a specific {@link Attraction}.
     *
     * @param id     the UUID of the {@link Attraction}
     * @param status the {@link CommonStatus} to set
     * @throws CustomException if the {@link Attraction} is not found
     */
    @Override
    public void updateStatus(UUID id, CommonStatus status) {
        attractionRepository.findById(id).ifPresentOrElse(attraction -> {
            attraction.setStatus(status);
            attractionRepository.save(attraction);
        }, () -> {
            throw new CustomException(ATTRACTION_NOT_FOUND);
        });
    }


    /**
     * Reorders a batch of {@link Attraction}s based on the provided list of {@link ReorderRequest}.
     *
     * @param requests the list containing IDs and new sort orders
     * @throws CustomException if any {@link Attraction} is not found
     */
    @Override
    @Transactional
    public void reorderAttraction(List<ReorderRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) return;
        for (ReorderRequest req : requests) {
            Attraction attraction = attractionRepository.findById(req.getId()).orElseThrow(() -> CustomException.of(ATTRACTION_NOT_FOUND));
            attraction.setSortOrder(req.getSortOrder());
            attractionRepository.save(attraction);
        }
    }

    /**
     * Returns a pageable list of {@link AttractionResponse}.
     *
     * @param filter the {@link BaseFilter} containing pagination details
     * @return a {@link PageableResponse} of attractions
     */
    @Override
    public PageableResponse<AttractionResponse> findAll(BaseFilter filter) {
        Page<AttractionResponse> responsePage = attractionRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()))
                .map(attractionMapper::toResponse);
        return attractionMapper.toPageableResponse(responsePage);
    }

    /**
     * Retrieves all {@link Attraction}s without pagination.
     *
     * @return list of all {@link AttractionResponse}
     */
    @Override
    public List<AttractionResponse> getAll() {
        return attractionRepository.findAll()
                .stream()
                .map(attractionMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Returns all {@link Attraction}s associated with a specific {@link Destination}.
     *
     * @param destinationId the UUID of the {@link Destination}
     * @return list of {@link AttractionResponse} sorted by {@code sortOrder}
     * @throws CustomException if the {@link Destination} is not found
     */
    @Override
    public List<AttractionResponse> getListByDestinationId(UUID destinationId) {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        return attractionRepository.findAllByDestinationIdOrderBySortOrderAsc(destination.getId()).stream()
                .map(attractionMapper::toResponse)
                .toList();
    }

    @Override
    public List<PublicAttractionResponse> getPublicListByDestinationId(UUID destinationId) {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        return attractionRepository.findAllByDestinationIdAndStatusOrderBySortOrderAsc(destination.getId(), CommonStatus.ACTIVE).stream()
                .map(attractionMapper::toPubResponse)
                .toList();
    }
}
