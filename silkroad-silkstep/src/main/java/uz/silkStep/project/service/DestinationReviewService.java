package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.DestinationReviewRequest;
import uz.silkStep.project.dto.response.pub.PublicDestinationReviewResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by: Diyora Alieva
 **/

public interface DestinationReviewService {

    void create(DestinationReviewRequest request);

    BigDecimal reportAggregate();

    List<PublicDestinationReviewResponse> getListByDestinationId(UUID destinationId);
}
