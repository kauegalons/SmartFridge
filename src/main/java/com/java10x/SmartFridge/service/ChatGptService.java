package com.java10x.SmartFridge.service;

import com.java10x.SmartFridge.dto.FoodDTO;
import com.java10x.SmartFridge.dto.OpenAiRequest;
import tools.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final WebClient webClient;

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<String> generateRecipe(List<FoodDTO> foodsDTO){
        String foods = foodsDTO
                .stream()
                .map(item -> String.format("%s (%s) - quantity: %d, expiration date: %s", item.getName(), item.getCategory(), item.getQuantity(), item.getExpirationDate()))
                .collect(Collectors.joining("\n"));

        String prompt = "Now you are a chef, based on the food at my database, make a recipe:" + foods;

        OpenAiRequest request = new OpenAiRequest("gpt-6-luna", prompt);
        return webClient.post()
                .uri("/responses")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    for (JsonNode item : json.path("output")) {
                        for (JsonNode content : item.path("content")) {
                            String recipe = content.path("text").asString();
                            if (!recipe.isBlank()) {
                                return recipe;
                            }
                        }
                    }
                    return "No recipe could be generated with the available ingredients.";
                });
    }
}
