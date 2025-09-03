package com.challenge.service;

import com.challenge.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Registra la clase como servicio de Spring (componente de negocio).
public class ProductService {

    // Lista inmutable que simula una "base de datos" en memoria.
    private final List<Product> products = List.of(
            new Product(1L, "Laptop", 1200.0),
            new Product(2L, "Mouse", 25.0)
    );

    // Metodo para obtener todos los productos.
    public List<Product> getAllProducts() {
        return products;
    }

    // Metodo para obtener un producto por su id
    public Optional<Product> getProductById(Long id) {
        return products.stream()               // crea flujo funcional de elementos
                .filter(p -> p.id().equals(id)) // filtra por id
                .findFirst();               // devuelve Optional con primer match o vacío
    }
}
