package com.java10x.SmartFridge.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ChatGptService {

    private final WebClient webClient;

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    private String apiKey = System.getenv("API_KEY");


    public Mono<String> generateRecipe(){
        String prompt = "Agora vc é um chefe de cozinha, me de uma receita com os ingrediente que tem disponivel";

    }
}
