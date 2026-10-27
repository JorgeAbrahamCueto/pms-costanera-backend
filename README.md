# 🏨 Costanera PMS - Backend (API REST)

Este repositorio contiene el código fuente del Backend (Nivel 0 - Sprint 1) para el Sistema de Gestión Hotelera (PMS) del Hotel Costanera. Su objetivo principal es centralizar la gestión de reservas, evitar el overbooking, administrar el estado de las habitaciones y procesar pagos mediante una API RESTful segura.

## ⚙️ Stack Tecnológico
* **Lenguaje:** Java 21+ (o superior)
* **Framework Core:** Spring Boot 3.x
* **Seguridad:** Spring Security, JWT (JSON Web Tokens) y encriptación BCrypt
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** MySQL
* **Herramientas Adicionales:** Lombok (para optimización de código)

## 🏗️ Arquitectura
El proyecto sigue el patrón de diseño Multicapas (MVC) para asegurar su escalabilidad:
* `controller/`: Controladores REST (Endpoints de la API).
* `service/`: Lógica de negocio y reglas del hotel.
* `repository/`: Capa de acceso a datos (Spring Data JPA).
* `entity/`: Entidades de la base de datos (Modelo Físico).

## 🚀 Inicio Rápido (Local)
1. Clonar el repositorio: `git clone https://github.com/tu-usuario/pms-costanera-backend.git`
2. Configurar las credenciales de MySQL en `src/main/resources/application.properties`.
3. Ejecutar el proyecto con Maven: `.\mvnw spring-boot:run`
