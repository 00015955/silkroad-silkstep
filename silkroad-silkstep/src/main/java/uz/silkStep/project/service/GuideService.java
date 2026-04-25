package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.GuideRequest;
import uz.silkStep.project.dto.response.GuideResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.filter.BaseFilter;

import java.util.List;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

public interface GuideService {

    void update(UUID id, GuideRequest request);

    void create(GuideRequest request);

    GuideResponse findById(UUID id);

    PageableResponse<GuideResponse> findAll(BaseFilter filter);

    List<GuideResponse> getAll();
}
/// The GuideService interface defines the contract for managing guides in the application. 
//It includes methods for creating and updating guides, retrieving guide information by ID, and fetching lists of guides based on filtering criteria or retrieving all guides. 
//This service is essential for handling operations related to guides, such as tour guides or informational guides, within the Silk Step project.