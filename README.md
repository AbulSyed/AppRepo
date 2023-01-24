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

Splunk

1. run splunk

```
docker run -d -p 8000:8000 -e "SPLUNK_START_ARGS=--accept-license" -e "SPLUNK_PASSWORD=<password>" --name splunk splunk/splunk:latest
```

2. setting/data inputs/Http event collector

3. new token and provide name and source name

4. select > log4j

5. create index

6. exclude spring boot logging library

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <exclusions>
    <exclusion>
      <artifactId>spring-boot-starter-logging</artifactId>
      <groupId>org.springframework.boot</groupId>
    </exclusion>
  </exclusions>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

7. add log statements

8. add splunk dependencies

```
```