package com.syed.authservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Value("${pom.version}")
    private String pomVersion;

    @GetMapping("/health")
    public String health() {
        return "Version " + pomVersion + " of Auth API service up";
    }
}
