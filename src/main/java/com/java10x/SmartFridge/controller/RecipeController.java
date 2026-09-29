package com.java10x.SmartFridge.controller;

import com.java10x.SmartFridge.dto.FoodDTO;
import com.java10x.SmartFridge.service.OpenAiService;
import com.java10x.SmartFridge.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final FoodItemService foodItemService;

    private final OpenAiService openAiService;

    public RecipeController(FoodItemService foodItemService, OpenAiService openAiService) {
        this.foodItemService = foodItemService;
        this.openAiService = openAiService;
    }


    @PostMapping
    public Mono<ResponseEntity<String>> generateRecipe(){
        List<FoodDTO> foodItems = foodItemService.listAllFood();

        return openAiService.generateRecipe(foodItems)
                .map(recipe -> ResponseEntity.ok().body(recipe))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

}
