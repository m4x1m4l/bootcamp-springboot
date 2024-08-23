package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.service.consumingrest.Quote;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TrumpCiteService {
    private final WebClient webClient;

    // Konstruktor-basierte Dependency Injection des WebClient
    public TrumpCiteService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://example.com").build();
    }

    public String getApiResponse(String endpoint) {
        Mono<Quote> response = webClient.get()
                .uri(endpoint) // URL-Endpunkt, an den die Anfrage gesendet wird
                .retrieve() // Initiert die Anfrage und verarbeitet die Antwort
                .bodyToMono(Quote.class); // Wandelt die Antwort in ein Mono-Objekt um

        Quote quote = response.block();
        return quote.getMessage();
    }
}
