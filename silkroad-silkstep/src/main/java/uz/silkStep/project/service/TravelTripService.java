package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.ReorderRequest;
import uz.silkStep.project.dto.request.TravelTripRequest;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.TravelTripResponse;
import uz.silkStep.project.dto.response.pub.PublicTravelTripResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;

import java.util.List;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

public interface TravelTripService {

    void update(UUID id, TravelTripRequest request);

    void create(TravelTripRequest request);

    TravelTripResponse findById(UUID id);

    void updateStatus(UUID id, CommonStatus status);

    void reorder(List<ReorderRequest> requests);

    PageableResponse<TravelTripResponse> findAll(BaseFilter filter);

    List<TravelTripResponse> getAll();

    List<PublicTravelTripResponse> getPubAll();
}
