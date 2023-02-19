package com.syed.reposervice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syed.reposervice.dto.UserRepo;
import com.syed.reposervice.exception.InternalServerErrorException;
import com.syed.reposervice.exception.NotFoundException;
import com.syed.reposervice.service.RepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class RepoServiceImpl implements RepoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepoService.class);

    private final RestTemplate restTemplate;

    public RepoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
}
