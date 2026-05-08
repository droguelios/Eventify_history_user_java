# Eventify API 📅

Plataforma de gestión de eventos construida con **Spring Boot 3.5.0** y **Java 21**. Esta aplicación implementa una arquitectura profesional siguiendo el patrón MVC, asegurando un código limpio, escalable y testeado.

## 🚀 Características
- **Arquitectura MVC**: Separación de responsabilidades entre Controlador, Servicio y Repositorio.
- **Persistencia en Memoria**: Uso de colecciones eficientes para simular el almacenamiento de datos.
- **Validaciones**: Lógica de negocio robusta integrada en la capa de servicios.
- **Documentación Interactiva**: Documentación automática de la API con **Swagger/OpenAPI**.
- **Unit Testing**: Suite de pruebas unitarias aisladas con **JUnit 5** y **Mockito**.

## 🛠️ Tecnologías
- **Java 21**
- **Spring Boot 3.5.0**
- **Lombok** (Para reducción de código repetitivo)
- **H2 Database** (En memoria para desarrollo)
- **SpringDoc OpenAPI** (Swagger UI)

## 📖 Cómo empezar
1. Asegúrate de tener instalado el **JDK 21**.
2. Clona el proyecto y entra en la carpeta `Eventify`.
3. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

## 🔗 Endpoints Principales
Todos los servicios están disponibles bajo el prefijo `/api`:
- `GET /api/events` - Listar todos los eventos.
- `POST /api/events` - Registrar un nuevo evento (valida nombre no vacío).
- `GET /api/venues` - Listar todos los lugares.
- `POST /api/venues` - Registrar un nuevo lugar (valida capacidad no nula).

## 📄 Documentación
Accede a la interfaz interactiva de Swagger en:
[http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)
