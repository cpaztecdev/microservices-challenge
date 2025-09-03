package com.challenge.controller;

import com.challenge.model.Product;
import com.challenge.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController               // Combina @Controller + @ResponseBody
@RequestMapping("/products")  // RUTA BASE: /products
public class ProductController {

    private final ProductService service;

    // Inyección de dependencia vía constructor
    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET /products
    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return service.getProductById(id)      // Optional<Product>
                .map(ResponseEntity::ok) // Si presente -> 200 OK
                .orElse(ResponseEntity.notFound().build()); // Si ausente -> 404
    }
}
