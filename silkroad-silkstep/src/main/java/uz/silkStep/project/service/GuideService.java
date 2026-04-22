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
