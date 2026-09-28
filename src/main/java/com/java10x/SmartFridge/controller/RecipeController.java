package com.java10x.SmartFridge.controller;

import com.java10x.SmartFridge.service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

    private ChatGptService chatGptService;

    public RecipeController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }


    @GetMapping("/recipe")
    public Mono<ResponseEntity<String>> generateRecipe{
        return chatGptService.generateRecipe();
    }

}
