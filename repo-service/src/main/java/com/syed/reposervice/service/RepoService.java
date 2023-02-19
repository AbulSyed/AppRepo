package com.syed.reposervice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syed.reposervice.dto.UserRepo;

public interface RepoService {

    UserRepo[] getRepos(String username) throws JsonProcessingException;
}
