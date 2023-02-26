package com.syed.reposervice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syed.reposervice.dto.RepoDto;
import com.syed.reposervice.dto.UserRepo;

import java.util.List;

public interface RepoService {

    UserRepo[] getRepos(String username) throws JsonProcessingException;
    RepoDto shareRepo(RepoDto repoDto);
    List<RepoDto> getSharedRepos();
}
