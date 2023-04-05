package com.syed.feedbackservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthController.class);

    @Value("${pom.version}")
    private String pomVersion;

    @GetMapping("/health")
    public String health() {
        LOGGER.info("Entering HealthController:health");

        return "Version " + pomVersion + " of Feedback API service up";
    }
}
