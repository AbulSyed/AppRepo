package com.syed.authservice.service.impl;

import com.syed.authservice.dto.UserDto;
import com.syed.authservice.entity.User;
import com.syed.authservice.repository.UserRepository;
import com.syed.authservice.service.UserService;
import com.syed.authservice.utility.AuthServiceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public UserServiceImpl(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    /**
     * fetches user details based on access token
     * @param accessToken the token sent by GitHub auth
     * @return the GitHub username
     */
    @Override
    public String getUserByToken(String accessToken) {
        LOGGER.info("Entering UserServiceImpl:getUserByToken");

        String url = "https://api.github.com/user";

        HttpHeaders headers = new HttpHeaders();
        headers.set(AuthServiceConstant.AUTHORIZATION, AuthServiceConstant.BEARER + accessToken);
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, UserDto.class);

        UserDto user = response.getBody();
        if (!userRepository.existsByUsername(user.getLogin())) {
            saveUser(user, accessToken);
        } else {
            updateUserToken(user.getLogin(), accessToken);
        }

        return user.getLogin();
    }

    /**
     * saved user to database
     * @param userDto the user object sent from request
     * @param accessToken the users access token
     */
    @Override
    public void saveUser(UserDto userDto, String accessToken) {
        LOGGER.info("Entering UserServiceImpl:saveUser");

        User user = new User();
        user.setUsername(userDto.getLogin());
        user.setAvatarUrl(userDto.getAvatar_url());
        user.setGithubUrl(userDto.getHtml_url());
        user.setRepoUrl(userDto.getRepos_url());
        user.setToken(accessToken);

        userRepository.save(user);
    }

    /**
     * updates users access token for every successful login
     * @param username the GitHub username
     * @param accessToken the token sent by GitHub auth
     */
    @Override
    public void updateUserToken(String username, String accessToken) {
        LOGGER.info("Entering UserServiceImpl:updateUserToken");

        // find user by username and update his token
        User user = userRepository.findByUsername(username);

        user.setToken(accessToken);
        userRepository.save(user);
    }

    /**
     * gets user object using GitHub username
     * @param username the GitHub username
     * @return the user dto
     */
    @Override
    public UserDto getUserByUsername(String username) {
        LOGGER.info("Entering UserServiceImpl:getUserByUsername");

        User user = userRepository.findByUsername(username);
        return new UserDto(user.getUsername(), user.getAvatarUrl(), user.getGithubUrl(), user.getRepoUrl());
    }
}
