package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.EventRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.EventResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.ReportEventResponse;
import uz.silkStep.project.dto.response.pub.PublicEventResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;

import java.util.List;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

public interface EventService {

    void create(EventRequest request);

    void update(UUID id, EventRequest request);

    void updateStatus(UUID id, CommonStatus status);

    void reorder(List<ReorderRequest> requests);

    EventResponse findById(UUID id);

    PageableResponse<EventResponse> findAll(BaseFilter filter);

    List<EventResponse> getAll();

    List<PublicEventResponse> getPubAll();

    ReportEventResponse getReport();
}
