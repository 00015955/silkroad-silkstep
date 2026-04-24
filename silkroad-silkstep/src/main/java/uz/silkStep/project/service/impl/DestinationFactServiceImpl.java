package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import uz.silkStep.project.domain.Destination;
import uz.silkStep.project.domain.DestinationFact;
import uz.silkStep.project.dto.request.DestinationFactRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.DestinationFactResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.pub.PublicDestinationFactResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.exception.ExceptionType;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.mapper.DestinationFactMapper;
import uz.silkStep.project.repository.DestinationFactRepository;
import uz.silkStep.project.repository.DestinationRepository;
import uz.silkStep.project.service.DestinationFactService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static uz.silkStep.project.exception.ExceptionType.DESTINATION_FACT_NOT_FOUND;

/**
 * Created by: Diyora Alieva
 **/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DestinationFactServiceImpl implements DestinationFactService {

    private final DestinationRepository destinationRepository;
    private final DestinationFactRepository destinationFactRepository;
    private final DestinationFactMapper destinationFactMapper;

    /**
     * Creates and persists a new {@link DestinationFact} entity associated with a specific {@link Destination}.
     * <p>
     * Retrieves the parent {@link Destination} by its ID and validates its existence.
     * Initializes multilingual labels and values, along with an optional icon and sort order.
     * <p>
     * Operation is transactional to ensure atomic persistence.
     *
     * @param request the {@link DestinationFactRequest} containing all necessary data to create a destination fact
     * @throws CustomException if the specified destination is not found
     */
    @Override
    @Transactional
    public void create(DestinationFactRequest request) {
        Destination destination = destinationRepository.findById(request.getDestinationId()).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        DestinationFact destinationFact = new DestinationFact();
        destinationFact.setDestinationId(destination.getId());
        destinationFact.setIcon(request.getIcon());
        destinationFact.setLabelEn(request.getLabelEn());
        destinationFact.setLabelUz(request.getLabelUz());
        destinationFact.setLabelRu(request.getLabelRu());
        destinationFact.setValueEn(request.getValueEn());
        destinationFact.setValueUz(request.getValueUz());
        destinationFact.setValueRu(request.getValueRu());
        destinationFact.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);

        destinationFactRepository.save(destinationFact);
    }

    /**
     * Updates an existing {@link DestinationFact} identified by its unique ID.
     * <p>
     * Validates existence of both the parent {@link Destination} and the destination fact itself.
     * Updates multilingual labels and values, icon, and sort order.
     * <p>
     * Transactional boundary ensures database consistency and prevents partial updates.
     *
     * @param id      the unique identifier of the destination fact
     * @param request the {@link DestinationFactRequest} containing updated data
     * @throws CustomException if the destination or the fact is not found
     */
    @Override
    @Transactional
    public void update(UUID id, DestinationFactRequest request) {
        Destination destination = destinationRepository.findById(request.getDestinationId()).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        DestinationFact destinationFact = destinationFactRepository.findById(id).orElseThrow(() -> CustomException.of(DESTINATION_FACT_NOT_FOUND));
        destinationFact.setDestinationId(destination.getId());
        destinationFact.setIcon(request.getIcon());
        destinationFact.setLabelEn(request.getLabelEn());
        destinationFact.setLabelUz(request.getLabelUz());
        destinationFact.setLabelRu(request.getLabelRu());
        destinationFact.setValueEn(request.getValueEn());
        destinationFact.setValueUz(request.getValueUz());
        destinationFact.setValueRu(request.getValueRu());
        destinationFact.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);

        destinationFactRepository.save(destinationFact);
    }

    /**
     * Retrieves a {@link DestinationFact} by its unique identifier.
     * <p>
     * Converts the entity into a {@link DestinationFactResponse} DTO for external consumption.
     *
     * @param id the unique identifier of the destination fact
     * @return the corresponding {@link DestinationFactResponse}
     * @throws CustomException if the destination fact is not found
     */
    @Override
    public DestinationFactResponse findById(UUID id) {
        DestinationFact destinationFact = destinationFactRepository.findById(id).orElseThrow(() -> CustomException.of(DESTINATION_FACT_NOT_FOUND));
        return destinationFactMapper.toResponse(destinationFact);
    }

    /**
     * Updates the status of a {@link DestinationFact}.
     * <p>
     * Useful for activating, deactivating, or otherwise managing the lifecycle state
     * of the fact without altering other fields.
     *
     * @param id     the unique identifier of the destination fact
     * @param status the new {@link CommonStatus} to apply
     * @throws CustomException if the destination fact is not found
     */
    @Override
    @Transactional
    public void updateStatus(UUID id, CommonStatus status) {
        destinationFactRepository.findById(id).ifPresentOrElse(destination -> {
            destination.setStatus(status);
            destinationFactRepository.save(destination);
        }, () -> {
            throw new CustomException(DESTINATION_FACT_NOT_FOUND);
        });
    }

    /**
     * Reorders multiple {@link DestinationFact} entities based on provided sort orders.
     * <p>
     * Iterates through the list of {@link ReorderRequest} objects, updating each fact's
     * sort order. If the list is empty, the operation is skipped.
     *
     * @param requests list of {@link ReorderRequest} specifying fact IDs and their new sort orders
     * @throws CustomException if any specified destination fact is not found
     */
    @Override
    @Transactional
    public void reorder(List<ReorderRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) return;
        for (ReorderRequest req : requests) {
            DestinationFact destinationFact = destinationFactRepository.findById(req.getId()).orElseThrow(() -> CustomException.of(DESTINATION_FACT_NOT_FOUND));
            destinationFact.setSortOrder(req.getSortOrder());
            destinationFactRepository.save(destinationFact);
        }
    }

    /**
     * Retrieves a paginated list of {@link DestinationFact} entities.
     * <p>
     * Maps each entity to {@link DestinationFactResponse} and wraps the results in
     * a {@link PageableResponse}.
     *
     * @param filter the {@link BaseFilter} containing pagination parameters
     * @return a {@link PageableResponse} of {@link DestinationFactResponse}
     */
    @Override
    public PageableResponse<DestinationFactResponse> findAll(BaseFilter filter) {
        Page<DestinationFactResponse> responsePage = destinationFactRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()))
                .map(destinationFactMapper::toResponse);
        return destinationFactMapper.toPageableResponse(responsePage);
    }

    /**
     * Retrieves all {@link DestinationFact} records without pagination.
     * <p>
     * Useful for scenarios where the full dataset is required.
     *
     * @return a list of {@link DestinationFactResponse} representing all destination facts
     */
    @Override
    public List<DestinationFactResponse> getAll() {
        return destinationFactRepository.findAll()
                .stream()
                .map(destinationFactMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all {@link DestinationFact} entities associated with a specific {@link Destination}.
     * <p>
     * The returned list is ordered by the fact's sort order, and each entity is mapped to
     * a {@link DestinationFactResponse} DTO.
     *
     * @param destinationId the unique identifier of the parent {@link Destination}
     * @return a list of {@link DestinationFactResponse} associated with the specified destination
     * @throws CustomException if the destination is not found
     */
    @Override
    public List<DestinationFactResponse> getListByDestinationId(UUID destinationId) {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        return destinationFactRepository.findAllByDestinationIdOrderBySortOrder(destination.getId()).stream()
                .map(destinationFactMapper::toResponse)
                .toList();
    }

    @Override
    public List<PublicDestinationFactResponse> getPubListByDestinationId(UUID destinationId) {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> CustomException.of(ExceptionType.DESTINATION_NOT_FOUND));
        return destinationFactRepository.findAllByDestinationIdAndStatusOrderBySortOrder(destination.getId(), CommonStatus.ACTIVE).stream()
                .map(destinationFactMapper::toPubResponse)
                .toList();
    }
}

// The DestinationFactServiceImpl class provides implementations for managing destination facts, including creation, updating, retrieval, status management, and reordering. 
//It ensures transactional integrity and proper exception handling throughout its operations.