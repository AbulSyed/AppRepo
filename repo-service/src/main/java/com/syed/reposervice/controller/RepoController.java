package com.syed.reposervice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syed.reposervice.dto.RepoDto;
import com.syed.reposervice.dto.UserRepo;
import com.syed.reposervice.service.RepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RepoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepoController.class);

    private final RepoService repoService;

    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("getRepos/{username}")
    public UserRepo[] getRepos(@PathVariable String username) throws JsonProcessingException {
        LOGGER.debug("Entering RepoController:getRepos");

        return repoService.getRepos(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/shareRepo")
    public RepoDto shareRepo(@RequestBody RepoDto repoDto) {
        return repoService.shareRepo(repoDto);
    }
}
