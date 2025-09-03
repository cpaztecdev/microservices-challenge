package com.challenge.client;

import com.challenge.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductClient {

    private final WebClient webClient;

    // Inyectamos WebClient.Builder y lo configuramos con la base URL del Product Service
    public ProductClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080/products").build();
    }

    // Hace GET /products/{id}
    public Mono<Product> getProductById(Long id) {
        return webClient.get()            // prepara petición GET
                .uri("/{id}", id) // setea path variable
                .retrieve()       // ejecuta y prepara el intercambio
                .bodyToMono(Product.class); // mapea el cuerpo a Mono<Product>
    }
}
