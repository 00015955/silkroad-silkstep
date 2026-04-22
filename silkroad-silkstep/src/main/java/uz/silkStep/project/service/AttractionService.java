package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.AttractionRequest;
import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.response.AttractionResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.pub.PublicAttractionResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;

import java.util.List;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

public interface AttractionService {

    void update(UUID id, AttractionRequest request);

    void create(AttractionRequest request);

    AttractionResponse findById(UUID id);

    void updateStatus(UUID id, CommonStatus status);

    void reorderAttraction(List<ReorderRequest> requests);

    PageableResponse<AttractionResponse> findAll(BaseFilter filter);

    List<AttractionResponse> getAll();

    List<AttractionResponse> getListByDestinationId(UUID destinationId);

    List<PublicAttractionResponse> getPublicListByDestinationId(UUID destinationId);
}
