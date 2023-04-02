package com.syed.reposervice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syed.reposervice.dto.RepoDto;
import com.syed.reposervice.dto.UserRepo;
import com.syed.reposervice.dto.UsernameDto;
import com.syed.reposervice.service.RepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getSharedRepos")
    public List<RepoDto> getSharedRepos(@RequestBody UsernameDto usernameDto) {
        LOGGER.info("Entering RepoController:getSharedRepos");

        return repoService.getSharedRepos(usernameDto);
    }

    /**
     * gets repos starred by user
     * @param usernameDto the GitHub username
     * @return list of repositories starred by user
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getStarredRepos")
    public List<RepoDto> getStarredRepos(@RequestBody UsernameDto usernameDto) {
        LOGGER.info("Entering RepoController:getStarredRepos");

        return repoService.getStarredRepos(usernameDto);
    }
}
