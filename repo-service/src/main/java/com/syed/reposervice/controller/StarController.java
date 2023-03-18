package com.syed.reposervice.controller;

import com.syed.reposervice.dto.StarredRepoDto;
import com.syed.reposervice.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StarController.class);

    private final StarService starService;

    public StarController(StarService starService) {
        this.starService = starService;
    }

    /**
     * endpoint to star and un-star a repo
     * @param starredRepoDto
     * @return
     */
    @PostMapping("/starRepo")
    public String starRepo(@RequestBody StarredRepoDto starredRepoDto) {
        LOGGER.debug("Entering StarController:starRepo");

        return starService.starRepo(starredRepoDto);
    }
}
