package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import uz.silkStep.project.domain.Event;
import uz.silkStep.project.dto.request.EventRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.EventResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.ReportEventResponse;
import uz.silkStep.project.dto.response.pub.PublicEventResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.mapper.EventMapper;
import uz.silkStep.project.repository.EventRepository;
import uz.silkStep.project.service.EventService;
import uz.silkStep.project.service.FileService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static uz.silkStep.project.exception.ExceptionType.EVENT_NOT_FOUND;

/**
 * Created by: Diyora Alieva
 **/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final FileService fileService;
    private final EventMapper eventMapper;

    /**
     * Creates and persists a new {@link Event} entity.
     * <p>
     * This method initializes an event with multilingual fields such as name and description,
     * along with metadata like event date, location, and sorting order. If a file is provided,
     * it is processed and stored via the file service, and the resulting file reference is linked
     * to the event.
     * <p>
     * Executed within a transactional context to ensure atomicity of both data persistence
     * and file association.
     *
     * @param request the {@link EventRequest} containing event details and optional file data
     */
    @Override
    @Transactional
    public void create(EventRequest request) {
        Event event = new Event();
        event.setEventDate(request.getEventDate());
        event.setLocation(request.getLocation());
        event.setNameEn(request.getNameEn());
        event.setNameUz(request.getNameUz());
        event.setNameRu(request.getNameRu());
        event.setDescriptionEn(request.getDescriptionEn());
        event.setDescriptionRu(request.getDescriptionRu());
        event.setDescriptionUz(request.getDescriptionUz());
        event.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);
        if (StringUtils.isNoneBlank(request.getFile())) {
            event.setImageUrl(fileService.saveAttachment(request.getFileName(), request.getFile()));
        }
        eventRepository.save(event);
    }


    /**
     * Updates an existing {@link Event} identified by the given ID.
     * <p>
     * The method updates all mutable fields including multilingual content,
     * scheduling information, location, and sorting order. If a new file is provided,
     * it replaces the existing file reference with a newly stored attachment.
     * <p>
     * The operation is executed within a transactional boundary to maintain consistency.
     *
     * @param id      the unique identifier of the event
     * @param request the {@link EventRequest} containing updated event data
     */
    @Override
    @Transactional
    public void update(UUID id, EventRequest request) {
        Event event = eventRepository.findById(id).orElseThrow(() -> CustomException.of(EVENT_NOT_FOUND));
        event.setNameEn(request.getNameEn());
        event.setNameUz(request.getNameUz());
        event.setNameRu(request.getNameRu());
        event.setDescriptionEn(request.getDescriptionEn());
        event.setDescriptionRu(request.getDescriptionRu());
        event.setDescriptionUz(request.getDescriptionUz());
        event.setEventDate(request.getEventDate());
        event.setLocation(request.getLocation());
        event.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);
        if (StringUtils.isNoneBlank(request.getFile())) {
            event.setImageUrl(fileService.saveAttachment(request.getFileName(), request.getFile()));
        }
        eventRepository.save(event);
    }

    /**
     * Updates the status of an {@link Event}.
     * <p>
     * This method is used to control the lifecycle of an event, such as enabling,
     * disabling, or archiving it. Only the status field is modified.
     *
     * @param id     the unique identifier of the event
     * @param status the new {@link CommonStatus} value
     */
    @Override
    @Transactional
    public void updateStatus(UUID id, CommonStatus status) {
        eventRepository.findById(id).ifPresentOrElse(destination -> {
            destination.setStatus(status);
            eventRepository.save(destination);
        }, () -> {
            throw new CustomException(EVENT_NOT_FOUND);
        });
    }

    /**
     * Reorders multiple {@link Event} entities based on provided sorting instructions.
     * <p>
     * Each request item specifies an event identifier and its corresponding sort order.
     * The method iterates through the list and updates each entity accordingly.
     * If the input list is empty, the operation is skipped.
     *
     * @param requests list of {@link ReorderRequest} containing ordering information
     */
    @Override
    @Transactional
    public void reorder(List<ReorderRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) return;
        for (ReorderRequest req : requests) {
            Event event = eventRepository.findById(req.getId()).orElseThrow(() -> CustomException.of(EVENT_NOT_FOUND));
            event.setSortOrder(req.getSortOrder());
            eventRepository.save(event);
        }
    }

    /**
     * Retrieves an {@link Event} by its unique identifier.
     * <p>
     * The entity is converted into a {@link EventResponse} DTO for safe external exposure.
     *
     * @param id the unique identifier of the event
     * @return the corresponding {@link EventResponse}
     */
    @Override
    public EventResponse findById(UUID id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> CustomException.of(EVENT_NOT_FOUND));
        return eventMapper.toResponse(event);
    }

    /**
     * Retrieves a paginated list of {@link Event} records.
     * <p>
     * Supports pagination through {@link BaseFilter}, enabling efficient handling
     * of large datasets. The results are mapped into response DTOs and wrapped
     * into a standardized pageable response structure.
     *
     * @param filter the {@link BaseFilter} containing pagination parameters
     * @return a {@link PageableResponse} of {@link EventResponse}
     */
    @Override
    public PageableResponse<EventResponse> findAll(BaseFilter filter) {
        Page<EventResponse> responsePage = eventRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()))
                .map(eventMapper::toResponse);
        return eventMapper.toPageableResponse(responsePage);
    }

    /**
     * Retrieves all {@link Event} records without pagination.
     * <p>
     * Intended for scenarios where full dataset retrieval is required
     * and the data volume is manageable.
     *
     * @return a list of {@link EventResponse} representing all events
     */
    @Override
    public List<EventResponse> getAll() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicEventResponse> getPubAll() {
        return eventRepository.findAllByStatusOrderBySortOrderAsc(CommonStatus.ACTIVE)
                .stream()
                .map(eventMapper::toPubResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReportEventResponse getReport() {
        List<EventResponse> eventResponses = eventRepository.findAllByStatusOrderBySortOrderAsc(CommonStatus.ACTIVE)
                .stream()
                .map(eventMapper::toResponse)
                .collect(Collectors.toList());
        ReportEventResponse reportEventResponse = new ReportEventResponse();
        reportEventResponse.setPopular(eventResponses);
        reportEventResponse.setCount(eventResponses.size());
        reportEventResponse.setMonthCount(eventRepository.countByBetweenDate());
        return reportEventResponse;
    }
}
