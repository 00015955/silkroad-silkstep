package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import uz.silkStep.project.domain.Destination;
import uz.silkStep.project.dto.request.DestinationRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.DestinationResponse;
import uz.silkStep.project.dto.response.DestinationStatItemResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.ReportDestinationResponse;
import uz.silkStep.project.dto.response.pub.PublicDestinationResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.enums.Language;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.exception.ExceptionType;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.mapper.DestinationMapper;
import uz.silkStep.project.repository.DestinationRepository;
import uz.silkStep.project.service.DestinationService;
import uz.silkStep.project.service.FileService;
import uz.silkStep.project.utils.SecurityUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static uz.silkStep.project.exception.ExceptionType.DESTINATION_NOT_FOUND;

/**
 * Created by: Diyora Alieva
 **/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final FileService fileService;
    private final DestinationMapper destinationMapper;

    /**
     * Creates and persists a new {@link Destination} entity.
     * <p>
     * Validates slug uniqueness to prevent conflicts. Initializes multilingual
     * fields for names and descriptions, along with metadata such as rating,
     * recommended days, sorting order, and associated statistical items.
     * <p>
     * If a file is provided, it is uploaded via the {@link FileService}, and the
     * returned URL is linked to the destination entity.
     * <p>
     * Executed within a transactional context to ensure atomicity of all operations.
     *
     * @param request the {@link DestinationRequest} containing all necessary data to create a destination
     * @throws CustomException if the slug already exists
     */
    @Override
    @Transactional
    public void create(DestinationRequest request) {
        if (destinationRepository.existsBySlug(request.getSlug())) {
            throw new CustomException(ExceptionType.EXISTS_SLUG_ALREADY);
        }

        Destination destination = new Destination();
        destination.setSlug(request.getSlug());
        destination.setNameEn(request.getNameEn());
        destination.setNameUz(request.getNameUz());
        destination.setNameRu(request.getNameRu());
        destination.setDescriptionEn(request.getDescriptionEn());
        destination.setDescriptionRu(request.getDescriptionRu());
        destination.setDescriptionUz(request.getDescriptionUz());
//        destination.setRating(request.getRating());
        destination.setRecommendedDays(request.getRecommendedDays());
        destination.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);
        destination.setStatItems(request.getStatItems());
        if (StringUtils.isNoneBlank(request.getFile())) {
            destination.setImageUrl(fileService.saveAttachment(request.getFileName(), request.getFile()));
        }
        destinationRepository.save(destination);
    }

    /**
     * Updates an existing {@link Destination} identified by the given ID.
     * <p>
     * Performs slug uniqueness validation if the slug is modified. Updates
     * multilingual fields, rating, recommended days, statistical items, sorting
     * order, and optionally replaces the image if a new file is provided.
     * <p>
     * Transactional boundary ensures consistency of database updates and file associations.
     *
     * @param id      the unique identifier of the destination
     * @param request the {@link DestinationRequest} containing updated data
     * @throws CustomException if the destination is not found or slug already exists
     */
    @Override
    @Transactional
    public void update(UUID id, DestinationRequest request) {
        Destination destination = destinationRepository.findById(id).orElseThrow(() -> CustomException.of(DESTINATION_NOT_FOUND));
        if (!destination.getSlug().equals(request.getSlug()) && destinationRepository.existsBySlug(request.getSlug())) {
            throw new CustomException(ExceptionType.EXISTS_SLUG_ALREADY);
        }

        destination.setSlug(request.getSlug());
        destination.setNameEn(request.getNameEn());
        destination.setNameUz(request.getNameUz());
        destination.setNameRu(request.getNameRu());
        destination.setDescriptionEn(request.getDescriptionEn());
        destination.setDescriptionRu(request.getDescriptionRu());
        destination.setDescriptionUz(request.getDescriptionUz());
//        destination.setRating(request.getRating());
        destination.setRecommendedDays(request.getRecommendedDays());
        destination.setStatItems(request.getStatItems());
        destination.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);
        if (StringUtils.isNoneBlank(request.getFile())) {
            destination.setImageUrl(fileService.saveAttachment(request.getFileName(), request.getFile()));
        }
        destinationRepository.save(destination);
    }

    /**
     * Updates the status of a {@link Destination}.
     * <p>
     * Used to activate, deactivate, or otherwise manage the lifecycle state of
     * the destination entity. Only the status field is affected.
     *
     * @param id     the unique identifier of the destination
     * @param status the new {@link CommonStatus} to apply
     * @throws CustomException if the destination is not found
     */
    @Override
    @Transactional
    public void updateStatus(UUID id, CommonStatus status) {
        destinationRepository.findById(id).ifPresentOrElse(destination -> {
            destination.setStatus(status);
            destinationRepository.save(destination);
        }, () -> {
            throw new CustomException(DESTINATION_NOT_FOUND);
        });
    }

    /**
     * Reorders multiple {@link Destination} entities based on provided sort orders.
     * <p>
     * Each {@link ReorderRequest} specifies a destination ID and its desired sort
     * order. Method iterates through the list and applies the ordering. If the list
     * is empty, the operation is skipped.
     *
     * @param requests list of {@link ReorderRequest} specifying destination IDs and their sort orders
     * @throws CustomException if any specified destination is not found
     */
    @Override
    @Transactional
    public void reorderDestinations(List<ReorderRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) return;
        for (ReorderRequest req : requests) {
            Destination destination = destinationRepository.findById(req.getId()).orElseThrow(() -> CustomException.of(DESTINATION_NOT_FOUND));
            destination.setSortOrder(req.getSortOrder());
            destinationRepository.save(destination);
        }
    }

    /**
     * Retrieves a {@link Destination} by its unique identifier.
     * <p>
     * Converts the entity to a {@link DestinationResponse} DTO for external usage.
     *
     * @param id the unique identifier of the destination
     * @return the corresponding {@link DestinationResponse}
     * @throws CustomException if the destination is not found
     */
    @Override
    public DestinationResponse findById(UUID id) {
        Destination destination = destinationRepository.findById(id).orElseThrow(() -> CustomException.of(DESTINATION_NOT_FOUND));
        return destinationMapper.toResponse(destination);
    }

    /**
     * Retrieves a paginated list of {@link Destination} entities.
     * <p>
     * Supports pagination via {@link BaseFilter}. Results are mapped into
     * response DTOs and wrapped in a {@link PageableResponse}.
     *
     * @param filter the {@link BaseFilter} containing pagination parameters
     * @return a {@link PageableResponse} of {@link DestinationResponse}
     */
    @Override
    public PageableResponse<DestinationResponse> findAll(BaseFilter filter) {
        Page<DestinationResponse> responsePage = destinationRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()))
                .map(destinationMapper::toResponse);
        return destinationMapper.toPageableResponse(responsePage);
    }

    /**
     * Retrieves all {@link Destination} records without pagination.
     * <p>
     * Useful when the entire dataset needs to be retrieved and the data volume
     * is manageable.
     *
     * @return a list of {@link DestinationResponse} representing all destinations
     */
    @Override
    public List<DestinationResponse> getAll() {
        return destinationRepository.findAll()
                .stream()
                .map(destinationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicDestinationResponse> getPublicAll() {
        return destinationRepository.findAllByStatusOrderBySortOrderAsc(CommonStatus.ACTIVE)
                .stream()
                .map(destinationMapper::toPubResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the statistical items associated with a specific {@link Destination}.
     * <p>
     * Each stat item is mapped to a {@link DestinationStatItemResponse} with its
     * value and label localized according to the current request language. Falls
     * back to English if the localized label is not available.
     *
     * @param id the unique identifier of the destination
     * @return a list of {@link DestinationStatItemResponse} for the destination
     * @throws CustomException if the destination is not found
     */
    @Override
    public List<DestinationStatItemResponse> getStatItems(UUID id) {
        Destination destination = destinationRepository.findById(id).orElseThrow(() -> CustomException.of(DESTINATION_NOT_FOUND));
        return destination.getStatItems().stream()
                .map(stat -> DestinationStatItemResponse.builder()
                        .value(stat.getValue())
                        .label(stat.getLabel().getOrDefault(SecurityUtils.getCurrentRequestLanguage(), stat.getLabel().get(Language.en)))
                        .build())
                .toList();
    }

    @Override
    public ReportDestinationResponse getReport() {
        List<DestinationResponse> destinationResponses = destinationRepository.findAllByStatusOrderBySortOrderAsc(CommonStatus.ACTIVE)
                .stream()
                .map(destinationMapper::toResponse)
                .collect(Collectors.toList());
        ReportDestinationResponse reportDestinationResponse = new ReportDestinationResponse();
        reportDestinationResponse.setPopular(destinationResponses);
        reportDestinationResponse.setCount(destinationResponses.size());
        reportDestinationResponse.setMonthCount(destinationRepository.countByBetweenDate());
        return reportDestinationResponse;
    }
}

// This class implements the business logic for managing Destination entities, including creation, updating, status management, reordering, and retrieval. 
//It interacts with the DestinationRepository for database operations and uses a DestinationMapper to convert between entities and DTOs. 
//The FileService is used for handling file uploads associated with destinations.