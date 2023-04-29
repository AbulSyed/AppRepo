package com.syed.reposervice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syed.reposervice.dto.RepoDto;
import com.syed.reposervice.dto.UserRepo;
import com.syed.reposervice.dto.UsernameDto;
import com.syed.reposervice.service.RepoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "Repo controller exposes endpoints: getRepos, shareRepo, getSharedRepos, getStarredRepos")
@RestController
public class RepoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepoController.class);

    private final RepoService repoService;

    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    /**
     * get user repositories
     * @param username the GitHub username
     * @return array of user repositories
     * @throws JsonProcessingException
     */
    @ApiOperation(value = "Endpoint to get user repositories")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "OK",
                    response = UserRepo[].class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Missing auth token"
            ),
            @ApiResponse(
                    code = 404,
                    message = "User not found"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Error occurred while fetching user repositories"
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("getRepos/{username}")
    public UserRepo[] getRepos(@RequestHeader("Authorization") String authToken,
                               @PathVariable String username) throws JsonProcessingException {
        LOGGER.info("Entering RepoController:getRepos");

        return repoService.getRepos(authToken, username);
    }

    /**
     * endpoint to share a repo
     * @param repoDto repo data to share
     * @return the shared repo
     */
    @ApiOperation(value = "Endpoint to share a repo")
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "CREATED",
                    response = RepoDto.class
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/shareRepo")
    public RepoDto shareRepo(@RequestBody RepoDto repoDto) {
        LOGGER.info("Entering RepoController:shareRepo");

        return repoService.shareRepo(repoDto);
    }

    /**
     * gets shared repositories
     * @return list of shared repositories
     */
    @ApiOperation(value = "Endpoint to get shared repositories")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "OK",
                    response = RepoDto[].class
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getSharedRepos")
    public List<RepoDto> getSharedRepos(@RequestBody UsernameDto usernameDto) {
        LOGGER.info("Entering RepoController:getSharedRepos");

        return repoService.getSharedRepos(usernameDto);
    }

    /**
     * gets repos starred by a user
     * @param usernameDto the GitHub username
     * @return map of category with value as list of repositories starred by user
     */
    @ApiOperation(value = "Endpoint to get repos starred by a user")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "OK",
                    response = Map.class,
                    responseContainer = "List"
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getStarredRepos")
    public Map<String, List<RepoDto>> getStarredRepos(@RequestBody UsernameDto usernameDto) {
        LOGGER.info("Entering RepoController:getStarredRepos");

        return repoService.getStarredRepos(usernameDto);
    }
}
