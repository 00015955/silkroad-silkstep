package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.DestinationFactRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.DestinationFactResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.pub.PublicDestinationFactResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;

import java.util.List;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

public interface DestinationFactService {

    void update(UUID id, DestinationFactRequest request);

    void create(DestinationFactRequest request);

    DestinationFactResponse findById(UUID id);

    void updateStatus(UUID id, CommonStatus status);

    void reorder(List<ReorderRequest> requests);

    PageableResponse<DestinationFactResponse> findAll(BaseFilter filter);

    List<DestinationFactResponse> getAll();

    List<DestinationFactResponse> getListByDestinationId(UUID destinationId);

    List<PublicDestinationFactResponse> getPubListByDestinationId(UUID destinationId);
}

// This interface defines the contract for the DestinationFactService, which includes methods for creating, updating, and retrieving destination facts, as well as managing their status and order.