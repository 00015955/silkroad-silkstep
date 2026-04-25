package uz.silkStep.project.service;

import uz.silkStep.project.dto.request.AIChatRequest;
import uz.silkStep.project.dto.response.pub.PublicAIChatResponse;

/**
 * Created by: Diyora Alieva
 **/

public interface AIService {

    PublicAIChatResponse chat(AIChatRequest request);
}

// This interface defines a service for handling AI chat interactions. 
//The `chat` method takes an `AIChatRequest` and returns a `PublicAIChatResponse`. 
//The implementation of this service would contain the logic to process the chat request and generate an appropriate response, potentially using AI models or external APIs.