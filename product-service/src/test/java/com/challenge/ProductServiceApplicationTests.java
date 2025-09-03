package com.challenge;

import com.challenge.model.Product;
import com.challenge.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceApplicationTests {

    // Creamos instancia directa del servicio
    private final ProductService service = new ProductService();

    @Test
    void testGetAllProducts() {
        // Verificamos que la lista tenga 2 elementos
        assertEquals(2, service.getAllProducts().size());
    }

    @Test
    void testGetProductById_found() {
        // Buscamos el producto cuyo id sea 1 y comprobamos sus datos.
        Optional<Product> p = service.getProductById(1L);
        assertTrue(p.isPresent());                 // Debe existir
        assertEquals("Laptop", p.get().name());    // Nombre esperado
    }

    @Test
    void testGetProductById_notFound() {
        // Probamos búsqueda por id inexistente -> Optional vacío
        Optional<Product> p = service.getProductById(99L);
        assertFalse(p.isPresent());
    }
}
