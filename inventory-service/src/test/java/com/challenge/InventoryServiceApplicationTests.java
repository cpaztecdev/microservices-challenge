package com.challenge;

import com.challenge.client.ProductClient;
import com.challenge.model.Product;
import com.challenge.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
class InventoryServiceApplicationTests {

    @Test
    void testGetInventoryWithProduct_found() {
        // Creamos mock del cliente
        ProductClient client;
        client = Mockito.mock(ProductClient.class);

        // Al invocar client.getProductById(1L) devolvemos un Mono con el Product esperado
        when(client.getProductById(1L))
                .thenReturn(Mono.just(new Product(1L, "Laptop", 1200.0)));

        // Inyectamos el mock en el servicio
        InventoryService service = new InventoryService(client);

        // Verificamos el Mono resultante emita el String esperado y complete
        StepVerifier.create(service.getInventoryWithProduct(1L))
                .expectNext("Product: Laptop, Stock: 10")
                .verifyComplete();
    }

    @Test
    void testGetInventoryWithProduct_notFound() {
        // Mock del cliente
        ProductClient client = Mockito.mock(ProductClient.class);
        InventoryService service = new InventoryService(client);

        // ProductId 99L no existe en inventario -> debe devolver mensaje alternativo
        StepVerifier.create(service.getInventoryWithProduct(99L))
                .expectNext("Product not found in inventory")
                .verifyComplete();
    }
}
