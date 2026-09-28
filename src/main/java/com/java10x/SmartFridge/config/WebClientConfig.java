package com.java10x.SmartFridge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${chat-gpt.base.url}")
    private String chatGptUrlApi;

    @Value("${api.key}")
    private String apiKey;

    @Bean
    public WebClient webClient (WebClient.Builder builder) {
        return builder
                .baseUrl(chatGptUrlApi)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }



}
