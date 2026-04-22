package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.silkStep.project.domain.Event;
import uz.silkStep.project.domain.EventTag;
import uz.silkStep.project.dto.request.EventTagRequest;
import uz.silkStep.project.dto.response.EventTagResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.pub.PublicEventTagResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.exception.ExceptionType;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.mapper.EventTagMapper;
import uz.silkStep.project.repository.EventRepository;
import uz.silkStep.project.repository.EventTagRepository;
import uz.silkStep.project.service.EventTagService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static uz.silkStep.project.exception.ExceptionType.EVENT_TAG_NOT_FOUND;

/**
 * Created by: Diyora Alieva
 **/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventTagServiceImpl implements EventTagService {

    private final EventRepository eventRepository;
    private final EventTagRepository eventTagRepository;
    private final EventTagMapper eventTagMapper;

    /**
     * Creates and persists a new {@link EventTag} associated with a specific event.
     * <p>
     * This method establishes a relationship between an event and its tag representation,
     * including multilingual tag values. The operation is executed within a transactional
     * context to ensure atomicity and consistency.
     *
     * @param request the {@link EventTagRequest} containing event reference and tag data
     */
    @Override
    @Transactional
    public void create(EventTagRequest request) {
        Event event = eventRepository.findById(request.getEventId()).orElseThrow(() -> CustomException.of(ExceptionType.EVENT_NOT_FOUND));
        EventTag eventTag = new EventTag();
        eventTag.setEventId(event.getId());
        eventTag.setTagEn(request.getTagEn());
        eventTag.setTagRu(request.getTagRu());
        eventTag.setTagUz(request.getTagUz());

        eventTagRepository.save(eventTag);
    }

    /**
     * Updates an existing {@link EventTag} identified by the given ID.
     * <p>
     * The method reassigns the associated event (if changed) and updates
     * multilingual tag values. Executed within a transactional boundary
     * to maintain data integrity.
     *
     * @param id      the unique identifier of the event tag
     * @param request the {@link EventTagRequest} containing updated values
     */
    @Override
    @Transactional
    public void update(UUID id, EventTagRequest request) {
        Event event = eventRepository.findById(request.getEventId()).orElseThrow(() -> CustomException.of(ExceptionType.EVENT_NOT_FOUND));
        EventTag eventTag = eventTagRepository.findById(id).orElseThrow(() -> CustomException.of(EVENT_TAG_NOT_FOUND));
        eventTag.setEventId(event.getId());
        eventTag.setTagEn(request.getTagEn());
        eventTag.setTagRu(request.getTagRu());
        eventTag.setTagUz(request.getTagUz());

        eventTagRepository.save(eventTag);
    }

    /**
     * Retrieves an {@link EventTag} by its unique identifier.
     * <p>
     * This method performs a read operation and converts the entity
     * into a response DTO suitable for external use.
     *
     * @param id the unique identifier of the event tag
     * @return the corresponding {@link EventTagResponse}
     */
    @Override
    public EventTagResponse findById(UUID id) {
        EventTag eventTag = eventTagRepository.findById(id).orElseThrow(() -> CustomException.of(EVENT_TAG_NOT_FOUND));
        return eventTagMapper.toResponse(eventTag);
    }

    /**
     * Updates the status of an {@link EventTag}.
     * <p>
     * This method is used for managing lifecycle states such as activation
     * or deactivation. Only the status field is affected during this operation.
     *
     * @param id     the unique identifier of the event tag
     * @param status the new {@link CommonStatus} value
     */
    @Override
    @Transactional
    public void updateStatus(UUID id, CommonStatus status) {
        eventTagRepository.findById(id).ifPresentOrElse(destination -> {
            destination.setStatus(status);
            eventTagRepository.save(destination);
        }, () -> {
            throw new CustomException(EVENT_TAG_NOT_FOUND);
        });
    }

    /**
     * Retrieves a paginated list of {@link EventTag} records.
     * <p>
     * Supports pagination through {@link BaseFilter}, enabling efficient handling
     * of large datasets. Results are mapped into response DTOs and returned
     * in a standardized pageable structure.
     *
     * @param filter the {@link BaseFilter} containing pagination parameters
     * @return a {@link PageableResponse} of {@link EventTagResponse}
     */
    @Override
    public PageableResponse<EventTagResponse> findAll(BaseFilter filter) {
        Page<EventTagResponse> responsePage = eventTagRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()))
                .map(eventTagMapper::toResponse);
        return eventTagMapper.toPageableResponse(responsePage);
    }

    /**
     * Retrieves all {@link EventTag} records without pagination.
     * <p>
     * Intended for scenarios where full dataset access is required
     * and the data volume is manageable.
     *
     * @return a list of {@link EventTagResponse} representing all event tags
     */
    @Override
    public List<EventTagResponse> getAll() {
        return eventTagRepository.findAll()
                .stream()
                .map(eventTagMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all {@link EventTag} records associated with a specific event.
     * <p>
     * The result is ordered based on identifier or predefined ordering logic,
     * ensuring consistent presentation of tags for the given event.
     *
     * @param eventId the unique identifier of the event
     * @return a list of {@link EventTagResponse} linked to the specified event
     */
    @Override
    public List<EventTagResponse> getListByEventId(UUID eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> CustomException.of(ExceptionType.EVENT_NOT_FOUND));
        return eventTagRepository.findAllByEventIdOrderById(event.getId()).stream()
                .map(eventTagMapper::toResponse)
                .toList();
    }

    @Override
    public List<PublicEventTagResponse> getPubListByEventId(UUID eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> CustomException.of(ExceptionType.EVENT_NOT_FOUND));
        return eventTagRepository.findAllByEventIdAndStatusOrderById(event.getId(), CommonStatus.ACTIVE).stream()
                .map(eventTagMapper::toPubResponse)
                .toList();
    }
}
