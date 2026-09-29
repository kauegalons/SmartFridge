package com.java10x.SmartFridge.service;

import com.java10x.SmartFridge.dto.FoodDTO;
import com.java10x.SmartFridge.dto.OpenAiRequest;
import tools.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenAiService {

    private final WebClient webClient;

    public OpenAiService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<String> generateRecipe(List<FoodDTO> foodItems){
        String ingredients = foodItems
                .stream()
                .map(item -> String.format("- %s (%s) - quantity: %d, expires on: %s",
                        item.getName(), item.getCategory(), item.getQuantity(), item.getExpirationDate()))
                .collect(Collectors.joining("\n"));

        String prompt = """
                You are a chef. Create a single recipe from the ingredients available in my fridge.

                Rules:
                - Use only the ingredients listed below, plus basic staples (salt, pepper, oil, water).
                - Stay within the available quantities.
                - Prioritize the ingredients closest to their expiration date.
                - If the ingredients are not enough for a coherent recipe, say so instead of inventing one.

                Reply with a title, the ingredients with amounts, and numbered steps.

                Today is %s.

                Available ingredients:
                %s
                """.formatted(LocalDate.now(), ingredients);

        OpenAiRequest request = new OpenAiRequest("gpt-6-luna", prompt);
        return webClient.post()
                .uri("/responses")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    for (JsonNode outputItem : json.path("output")) {
                        for (JsonNode content : outputItem.path("content")) {
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
