package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.EventTagRequest;
import uz.silkStep.project.dto.response.EventTagResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.pub.PublicEventTagResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;

import java.util.List;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

public interface EventTagService {

    void update(UUID id, EventTagRequest request);

    void create(EventTagRequest request);

    EventTagResponse findById(UUID id);

    void updateStatus(UUID id, CommonStatus status);

    PageableResponse<EventTagResponse> findAll(BaseFilter filter);

    List<EventTagResponse> getAll();

    List<EventTagResponse> getListByEventId(UUID eventId);

    List<PublicEventTagResponse> getPubListByEventId(UUID eventId);
}

/// The EventTagService interface defines the contract for managing event tags in the application. 
//It includes methods for creating and updating event tags, changing their status, and retrieving event tag information based on various criteria. 
//The service allows for both administrative operations (like updating and changing status) and public retrieval of event tags associated with specific events.