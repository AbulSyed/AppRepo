# AppRepo

### Purpose
Platform acts as a hub where you can find reusble code. Login using your GitHub to share repositories.

### Technologies

#### UI
- React & TypeScript
- Redux toolkit

#### Backend
- Spring Boot
- Spring Cloud Gateway - entry point for UI to API endpoints, request to API gateway is routed to the specific microservice (port 2222)
- Spring Cloud Netflix Eureka server - allows services to find and communicate with other services (port 8761)
- MySQL