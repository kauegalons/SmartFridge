package com.java10x.SmartFridge.controller;

import com.java10x.SmartFridge.dto.FoodDTO;
import com.java10x.SmartFridge.service.ChatGptService;
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

    private FoodItemService foodItemService;

    private ChatGptService chatGptService;

    public RecipeController(FoodItemService foodItemService, ChatGptService chatGptService) {
        this.foodItemService = foodItemService;
        this.chatGptService = chatGptService;
    }


    @PostMapping
    public Mono<ResponseEntity<String>> generateRecipe(){
        List<FoodDTO> foodsDTO = foodItemService.listAllFood();

        return chatGptService.generateRecipe(foodsDTO)
                .map(recipe -> ResponseEntity.ok().body(recipe))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

}
