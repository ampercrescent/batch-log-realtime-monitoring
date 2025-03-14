package com.ampercrescent.blrm.service;

import com.ampercrescent.blrm.config.AppProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class LogService {
    private final WebClient webClient;

    public LogService(WebClient.Builder webClientBuilder, AppProperties appProperties) {
        this.webClient = webClientBuilder.baseUrl(appProperties.getElasticsearch().getBaseUrl()).build();
    }

    public Mono<String> saveLog(String logMessage) {
        String documentId = UUID.randomUUID().toString();
        return webClient.post()
                .uri("/log-ind/_doc/" + documentId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"message\": \"" + logMessage + "\"}")
                .retrieve()
                .bodyToMono(String.class);
    }
}