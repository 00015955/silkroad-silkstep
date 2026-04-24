package uz.silkStep.project.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatModel chatModel) {   // Spring AI creates automatic ChatModel
        return ChatClient.builder(chatModel)
                .defaultSystem("Siz foydali, aniq va do'stona yordamchisiz. O'zbek tilida javob bering.")
                // .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory())) // optional: add memory advisor for context retention
                .build();
    }
}

//AiConfig → Gemini AI setup. This configuration class defines a bean for the ChatClient, which is used to interact with the Gemini AI chat model. The chatClient method takes a ChatModel as a parameter (which is automatically created by Spring AI) and configures the ChatClient with a default system message that instructs the AI to be helpful, accurate, and friendly, and to respond in Uzbek. The commented-out line shows how you could optionally add a memory advisor for context retention, allowing the AI to remember previous interactions.