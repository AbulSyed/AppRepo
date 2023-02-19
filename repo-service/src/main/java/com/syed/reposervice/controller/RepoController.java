package com.syed.reposervice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syed.reposervice.dto.UserRepo;
import com.syed.reposervice.service.RepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RepoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepoController.class);

    private final RepoService repoService;

    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("getRepos/{username}")
    public UserRepo[] getRepos(@PathVariable String username) throws JsonProcessingException {
        LOGGER.debug("Entering RepoController:getRepos");

        return repoService.getRepos(username);
    }
}
