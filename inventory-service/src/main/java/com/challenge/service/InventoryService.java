package com.challenge.service;

import com.challenge.client.ProductClient;
import com.challenge.model.Inventory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class InventoryService {

    private final ProductClient productClient;

    // Inventario en memoria (inmutable)
    private final List<Inventory> inventories = List.of(
            new Inventory(1L, 10),
            new Inventory(2L, 50)
    );

    // Constructor: inyectamos el cliente HTTP reactivo
    public InventoryService(ProductClient productClient) {
        this.productClient = productClient;
    }

    // Retorna todos los inventarios como Flux
    public Flux<Inventory> getAllInventories() {
        return Flux.fromIterable(inventories);
    }

    // Busca el registro de inventario en la lista
    public Mono<String> getInventoryWithProduct(Long productId) {
        return inventories.stream()
                .filter(i -> i.productId().equals(productId)) // filtrado funcional
                .findFirst() // Optional<Inventory>
                .map(inv -> productClient.getProductById(productId) // Mono<Product>
                        .map(p -> "Product: " + p.name() + ", Stock: " + inv.quantity())) // Mono<String>
                .orElse(Mono.just("Product not found in inventory")); // Mono<String> alternativo
    }
}
