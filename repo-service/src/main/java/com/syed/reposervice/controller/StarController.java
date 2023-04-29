package com.syed.reposervice.controller;

import com.syed.reposervice.dto.StarredRepoDto;
import com.syed.reposervice.dto.UserRepo;
import com.syed.reposervice.service.StarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Star controller exposes endpoints: starRepo")
@RestController
public class StarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StarController.class);

    private final StarService starService;

    public StarController(StarService starService) {
        this.starService = starService;
    }

    /**
     * endpoint to star and un-star a repo
     * @param starredRepoDto the repository to star/un-star
     * @return string message
     */
    @ApiOperation(value = "Endpoint to star and un-star a repo")
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "CREATED",
                    response = UserRepo[].class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Repository not found"
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/starRepo")
    public String starRepo(@RequestBody StarredRepoDto starredRepoDto) {
        LOGGER.info("Entering StarController:starRepo");

        return starService.starRepo(starredRepoDto);
    }
}
