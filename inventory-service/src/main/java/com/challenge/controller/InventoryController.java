package com.challenge.controller;

import com.challenge.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // GET /inventory -> retorna todos los inventarios (Flux)
    @GetMapping
    public Flux<?> getAll() {
        return service.getAllInventories();
    }

    /*
     GET /inventory/{productId}
    */
    @GetMapping("/{productId}")
    public Mono<String> getInventoryWithProduct(@PathVariable Long productId) {
        return service.getInventoryWithProduct(productId);
    }
}
