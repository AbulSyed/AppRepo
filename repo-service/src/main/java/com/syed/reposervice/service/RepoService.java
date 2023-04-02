package com.syed.reposervice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syed.reposervice.dto.RepoDto;
import com.syed.reposervice.dto.UserRepo;
import com.syed.reposervice.dto.UsernameDto;

import java.util.List;
import java.util.Map;

public interface RepoService {

    UserRepo[] getRepos(String authToken, String username) throws JsonProcessingException;
    RepoDto shareRepo(RepoDto repoDto);
    List<RepoDto> getSharedRepos(UsernameDto usernameDto);
    Map<String, List<RepoDto>> getStarredRepos(UsernameDto usernameDto);
}
