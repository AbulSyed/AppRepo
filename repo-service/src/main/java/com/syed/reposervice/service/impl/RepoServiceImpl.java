package com.syed.reposervice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syed.reposervice.dto.RepoDto;
import com.syed.reposervice.dto.UserRepo;
import com.syed.reposervice.entity.Repo;
import com.syed.reposervice.exception.InternalServerErrorException;
import com.syed.reposervice.exception.NotFoundException;
import com.syed.reposervice.repository.RepoRepository;
import com.syed.reposervice.service.RepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepoServiceImpl implements RepoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepoService.class);

    private final RestTemplate restTemplate;
    private final RepoRepository repoRepository;

    public RepoServiceImpl(RestTemplate restTemplate, RepoRepository repoRepository) {
        this.restTemplate = restTemplate;
        this.repoRepository = repoRepository;
    }

    /**
     * makes a call to GitHub API and returns list of users repositories
     * @param username the GitHub username
     * @return the users repositories
     * @throws JsonProcessingException
     */
    @Override
    public UserRepo[] getRepos(String username) throws JsonProcessingException {
        LOGGER.debug("Entering RepoServiceImpl:getRepos");

        String url = "https://api.github.com/users/" + username + "/repos";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response.getBody(), UserRepo[].class);
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("User not found");
            }
            throw new InternalServerErrorException("Error occurred while fetching user repositories");
        }
    }

    /**
     * saves repository in database
     * @param repoDto the repo to share
     * @return the repo shared
     */
    @Override
    public RepoDto shareRepo(RepoDto repoDto) {
        LOGGER.debug("Entering RepoServiceImpl:shareRepo");

        Repo repo = new Repo();
        repo.setName(repoDto.getName());
        repo.setDescription(repoDto.getDescription());
        repo.setHtmlUrl(repoDto.getHtml_url());
        repo.setLanguage(repoDto.getLanguage());
        repo.setCloneUrl(repoDto.getClone_url());
        repo.setCategory(repoDto.getCategory());
        repo.setTech(repoDto.getTech());

        repoRepository.save(repo);
        repoDto.setId(repo.getId());

        return repoDto;
    }

    /**
     * method to fetch shared repos
     * @return list of shared repos
     */
    @Override
    public List<RepoDto> getSharedRepos() {
        LOGGER.debug("Entering RepoServiceImpl:getSharedRepos");

        List<Repo> repos = repoRepository.findAll();
        List<RepoDto> repoDtos = new ArrayList<>();

        for (Repo repo : repos) {
            RepoDto repoDto = new RepoDto(repo.getId(), repo.getName(), repo.getDescription(), repo.getHtmlUrl(),
                    repo.getLanguage(), repo.getCloneUrl(), repo.getCategory(), repo.getTech());
            repoDtos.add(repoDto);
        }

        return repoDtos;
    }
}
