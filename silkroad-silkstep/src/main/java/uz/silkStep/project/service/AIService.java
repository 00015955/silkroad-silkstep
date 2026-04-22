package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.AIChatRequest;
import uz.silkStep.project.dto.response.pub.PublicAIChatResponse;

/**
 * Created by: Diyora Alieva
 **/

public interface AIService {

    PublicAIChatResponse chat(AIChatRequest request);
}
