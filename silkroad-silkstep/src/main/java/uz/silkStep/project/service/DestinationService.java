package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.DestinationRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.DestinationResponse;
import uz.silkStep.project.dto.response.DestinationStatItemResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.ReportDestinationResponse;
import uz.silkStep.project.dto.response.pub.PublicDestinationResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;

import java.util.List;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

/// Service interface for managing destinations, including creation, updating, status changes, reordering, and retrieval of destination information and statistics.
public interface DestinationService {

    void create(DestinationRequest request);

    void update(UUID id, DestinationRequest request);

    void updateStatus(UUID id, CommonStatus status);

    void reorderDestinations(List<ReorderRequest> requests);

    DestinationResponse findById(UUID id);

    PageableResponse<DestinationResponse> findAll(BaseFilter filter);

    List<DestinationResponse> getAll();

    List<PublicDestinationResponse> getPublicAll();

    List<DestinationStatItemResponse> getStatItems(UUID id);

    ReportDestinationResponse getReport();
}

