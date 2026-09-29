package com.java10x.SmartFridge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${openai.api-url}")
    private String openAiApiUrl;

    @Value("${openai.api-key}")
    private String apiKey;

    @Bean
    public WebClient webClient (WebClient.Builder builder) {
        return builder
                .baseUrl(openAiApiUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }



}
