package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.TripPlanRequest;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.dto.response.TripPlanResponse;
import uz.silkStep.project.enums.CommonStatus;
import uz.silkStep.project.filter.BaseFilter;

import java.util.List;
import java.util.UUID;

public interface TripPlanService {

    void update(UUID id, TripPlanRequest request);

    void create(TripPlanRequest request);

    TripPlanResponse findById(UUID id);

    void updateStatus(UUID id, CommonStatus status);

    PageableResponse<TripPlanResponse> findAll(BaseFilter filter);

    List<TripPlanResponse> getAll();

    List<TripPlanResponse> getListByDestinationId(UUID destinationId);
}
/// The TripPlanService interface defines the contract for managing trip plans in the application, including creating, updating, retrieving, and changing the status of trip plans, as well as fetching trip plans based on specific criteria such as destination ID.