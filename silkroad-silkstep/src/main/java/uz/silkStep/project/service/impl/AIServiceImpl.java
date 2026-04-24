package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import uz.silkStep.project.domain.Destination;
import uz.silkStep.project.dto.request.AIChatRequest;
import uz.silkStep.project.dto.response.pub.PublicAIChatResponse;
import uz.silkStep.project.repository.DestinationRepository;
import uz.silkStep.project.service.AIService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/// AIServiceImpl is responsible for handling AI chat interactions related to Uzbekistan travel.
@Slf4j
@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final DestinationRepository destinationRepository;
    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = """
        You are Silkstep — an AI travel assistant exclusively for Uzbekistan tourism.
        
        === IDENTITY ===
        You help travelers discover Uzbekistan's destinations, culture, food, history, and travel tips.
        You are warm, friendly, inspiring, and knowledgeable about Uzbekistan.
        
        === LANGUAGE ===
        - Detect the user's language from their message.
        - Always reply in the SAME language: Uzbek, Russian, or English.
        - Never mix languages in one response.
        
        === SCOPE ===
        - ONLY answer questions related to Uzbekistan travel:
          destinations, cities, food, culture, history, traditions, weather, transport within Uzbekistan.
        - If the user asks about other countries or off-topic subjects, politely decline in their language.
          Example (UZ): "Uzr, men faqat O'zbekiston sayohati haqida yordam bera olaman."
          Example (RU): "Извините, я могу помочь только с путешествиями по Узбекистану."
          Example (EN): "Sorry, I can only assist with travel in Uzbekistan."
        - For visa details, official prices, or transport schedules — advise consulting official sources.
        
        === RESPONSE FORMAT ===
        IMPORTANT: Always respond in clean HTML format using only inner body tags.
        Never use <html>, <head>, or <body> tags.
        Never use Markdown (no **, no ##, no - lists with dashes).
        
        Use these HTML tags properly:
        - <h3> for section titles
        - <p> for paragraphs
        - <ul> and <li> for lists
        - <strong> for emphasis
        - <br> for line breaks if needed
        
        Example structure:
        <h3>Samarqand</h3>
        <p>Samarqand — O'zbekistonning eng qadimiy va go'zal shaharlaridan biri...</p>
        <ul>
          <li><strong>Registon maydoni</strong> — Sharqning eng ulug'vor me'moriy ansambli.</li>
          <li><strong>Shahrisabz</strong> — Amir Temur vatani.</li>
        </ul>
        <p>Bu shaharni ziyorat qilish uchun bahor va kuz mavsumi eng qulay.</p>
        
        === LENGTH ===
        - Keep responses concise: 3–5 paragraphs or equivalent HTML blocks.
        - Do not overwhelm the user with too much information at once.
        """;

    @Override
    public PublicAIChatResponse chat(AIChatRequest request) {
        String context = Optional.ofNullable(request.getDestinationSlug())
                .map(String::strip)
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)
                .flatMap(destinationRepository::findBySlug)
                .map(this::buildDestinationContext)
                .orElse("");

        String userPrompt = buildUserPrompt(context, request.getMessage());

        String answer = chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(userPrompt)
                .call()
                .content();

        return PublicAIChatResponse.builder()
                .message(answer)
                .destinationSlug(request.getDestinationSlug())
                .timestamp(Instant.now())
                .build();
    }

    /**
     * Prepares destination data in text format for the LLM.
     */
    private String buildDestinationContext(Destination destination) {
        StringBuilder sb = new StringBuilder(512);

        sb.append("=== ").append(destination.getName()).append(" ===\n")
                .append("Description: ").append(destination.getDescription()).append("\n")
                .append("Recommended days: ").append(destination.getRecommendedDays()).append("\n")
                .append("Rating: ").append(destination.getRating()).append("/5\n");

        /* "At a Glance" facts */
        appendSection(sb, "Key facts", destination.getFacts(),
                f -> "- " + f.getLabel() + ": " + f.getValue());

        /*Attractions*/
        appendSection(sb, "Attractions", destination.getAttractions(),
                a -> "- " + a.getName() +
                        (a.getDescription() != null ? ": " + a.getDescription() : ""));

        return sb.toString();
    }

    /**
     * Adds a list section to the StringBuilder (skips it if empty).
     */
    private <T> void appendSection(StringBuilder sb, String title,
                                   List<T> items,
                                   Function<T, String> formatter) {
        if (!CollectionUtils.isEmpty(items)) {
            sb.append("\n").append(title).append(":\n");
            items.forEach(item -> sb.append(formatter.apply(item)).append("\n"));
        }
    }

    /**
     * Creates a user prompt by adding context to the user's question.
     * The system prompt is NOT repeated here.
     */
    private String buildUserPrompt(String context, String message) {
        if (context.isBlank()) {
            return message;
        }
        return "Context:\n" + context + "\n\nUser question: " + message;
    }
}
