package com.java10x.SmartFridge.service;

import com.java10x.SmartFridge.dto.OpenAiRequest;
import tools.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ChatGptService {

    private final WebClient webClient;

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<String> generateRecipe(){
        String prompt = "Agora vc é um chefe de cozinha, me de uma receita com os ingrediente que tem disponivel";

        OpenAiRequest request = new OpenAiRequest("gpt-6-luna", prompt);
        return webClient.post()
                .uri("/responses")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    String recipe = json.path("output").path(0)
                            .path("content").path(0)
                            .path("text")
                            .asString();

                    if (recipe.isBlank()) {
                        return "Nenhuma receita pôde ser gerada com os ingredientes disponíveis.";
                    }
                    return recipe;
                });
    }
}
