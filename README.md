# 📦 Microservicios con Spring Boot 3 + Java 17

Este proyecto contiene **dos microservicios** que se comunican entre sí usando **HTTP reactivo con WebClient**, aplicando **Programación Funcional** y **Programación Reactiva** en Java 17.

---

## 🚀 Tecnologías utilizadas

- **Java 17**  
- **Spring Boot 3.x**  
- **Spring WebFlux** (para endpoints reactivos y comunicación entre servicios)  
- **Records de Java** (para inmutabilidad y DTOs)  
- **Programación Funcional** (Streams, Lambdas, Optional)  
- **Programación Reactiva** (Mono, Flux)  
- **JUnit 5 + Mockito + Reactor Test** (para pruebas unitarias)  
- **Maven** (gestión de dependencias y build)  

---

## 🏗️ Arquitectura

📌 Microservicios independientes:
        
```
microservices-example/
│
├── product-service/        # Microservicio A - gestiona productos
│   ├── GET /products
│   └── GET /products/{id}
│
└── inventory-service/      # Microservicio B - gestiona inventario y consulta productos
    ├── GET /inventory
    └── GET /inventory/{productId}
```

### Flujo de comunicación
1. El **Inventory Service** consulta su inventario local.  
2. Llama vía **WebClient** al **Product Service** (`http://localhost:8080/products/{id}`).  
3. Combina la información del producto con el stock disponible.  

---

## ⚙️ Instalación y ejecución

### 1️⃣ Clonar repositorio
```bash
git clone https://github.com/tu-usuario/microservices-example.git
cd microservices-example
```

### 2️⃣ Compilar ambos servicios
```bash
cd product-service
mvn clean install

cd ../inventory-service
mvn clean install
```

### 3️⃣ Ejecutar servicios

En dos terminales distintas:
```bash
# Terminal 1 - Product Service (puerto 8080)
cd product-service
mvn spring-boot:run

# Terminal 2 - Inventory Service (puerto 8081)
cd inventory-service
mvn spring-boot:run
```

---

## 🌐 Endpoints disponibles
## 🟢 Product Service (http://localhost:8080)

- GET /products → Lista de productos
```json
[
  {"id":1,"name":"Laptop","price":1200.0},
  {"id":2,"name":"Mouse","price":25.0}
]
```

- GET /products/1 → Detalle de un producto
```json
{"id":1,"name":"Laptop","price":1200.0}
```

## 🟠 Inventory Service (http://localhost:8081)

- GET /inventory → Lista de inventario
```json
[
  {"productId":1,"quantity":10},
  {"productId":2,"quantity":50}
]
```

- GET /inventory/1 → Inventario + producto combinado
```yaml
Product: Laptop, Stock: 10
```

• GET /inventory/99 → Producto no encontrado
```mathematica
Product not found in inventory
```

---


## 🧪 Pruebas unitarias

Ejecutar pruebas con Maven:
```bash
mvn test
```

- ProductServiceTest → Valida búsqueda de productos (Optional, Streams).<br>
- InventoryServiceTest → Mockea ProductClient con Mockito y valida respuestas reactivas con StepVerifier.

---

## 📂 Estructura del proyecto

```swift
microservices-example/
│
├── product-service/
│   ├── src/main/java/com/example/productservice/
│   │   ├── ProductServiceApplication.java
│   │   ├── model/Product.java
│   │   ├── service/ProductService.java
│   │   └── controller/ProductController.java
│   └── src/test/java/com/example/productservice/...
│
└── inventory-service/
    ├── src/main/java/com/example/inventoryservice/
    │   ├── InventoryServiceApplication.java
    │   ├── model/Inventory.java
    │   ├── model/Product.java
    │   ├── client/ProductClient.java
    │   ├── service/InventoryService.java
    │   └── controller/InventoryController.java
    └── src/test/java/com/example/inventoryservice/...
```

## 📝 Notas importantes

- Para producción, deberías usar una base de datos en lugar de listas en memoria.<br>
- Se recomienda añadir manejo de errores en WebClient (onStatus(...)).<br>
- Podrías extender el proyecto con:<br>
     - Docker Compose para levantar ambos servicios juntos.<br>
     - Circuit Breaker (Resilience4j) para tolerancia a fallos.<br>
     - Spring Cloud Gateway para unificar entrada de API.

---

## 👨‍💻 Autor

Proyecto desarrollado en **Java 17** + **Spring Boot 3** por **CARLOS PAZ URCIA**.<br>
Ideal para practicar microservicios, programación funcional y reactiva.
