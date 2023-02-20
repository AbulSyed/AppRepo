# AppRepo

### Purpose
Application that allows users to share and find useful code repositories. Users can login using their GitHub credentials to share repositories.

### Architecture overview

```
                                     |----------|     |--------------|
                               |-----| AUTH API |-----| GITHUB OAUTH |
|--------|     |---------|     |     |----------|     |--------------|
| Client |-----| GATEWAY |-----|
|--------|     |---------|     |     |----------|
                               |-----| REPO API |
                                     |----------|
```

### Technologies

#### UI
- React & TypeScript
- Redux Toolkit
- Ant Design

#### Backend
- Spring Boot
- Spring Cloud Gateway - entry point for UI to API endpoints, request to API gateway is routed to the specific microservice (port 2222)
- Spring Cloud Netflix Eureka server - allows services to find and communicate with other services (port 8761)
- MySQL