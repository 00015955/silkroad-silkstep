package uz.silkStep.project.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by: Tilepbaev Dawletbay Ong`arbay uli
 * Date: 08.04.2026 12:01
 **/

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatModel chatModel) {   // Spring AI avtomatik ChatModel yaratadi
        return ChatClient.builder(chatModel)
                .defaultSystem("Siz foydali, aniq va do'stona yordamchisiz. O'zbek tilida javob bering.")
                // .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory())) // agar suhbat xotirasi kerak bo'lsa
                .build();
    }
}
