package com.syed.authservice.service.impl;

import com.syed.authservice.dto.UserDto;
import com.syed.authservice.entity.UserEntity;
import com.syed.authservice.exception.NotFoundException;
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
     * fetches user details from GitHub API using auth token
     * then saves or updates user in database
     * @param accessToken the token sent by GitHub auth
     */
    @Override
    public void saveOrUpdateUserToken(String accessToken) {
        LOGGER.info("Entering UserServiceImpl:saveOrUpdateUserToken");

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
    }

    /**
     * saves user to database
     * @param userDto the user object sent from request
     * @param accessToken the users access token
     */
    private void saveUser(UserDto userDto, String accessToken) {
        LOGGER.info("Entering UserServiceImpl:saveUser");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getLogin());
        userEntity.setAvatarUrl(userDto.getAvatar_url());
        userEntity.setGithubUrl(userDto.getHtml_url());
        userEntity.setRepoUrl(userDto.getRepos_url());
        userEntity.setToken(accessToken);
        userEntity.setAdmin(false);

        userRepository.save(userEntity);
    }

    /**
     * updates users access token for every successful login
     * @param username the GitHub username
     * @param accessToken the token sent by GitHub auth
     */
    private void updateUserToken(String username, String accessToken) {
        LOGGER.info("Entering UserServiceImpl:updateUserToken");

        // find user by username and update his token
        UserEntity userEntity = userRepository.findByUsername(username);

        userEntity.setToken(accessToken);
        userRepository.save(userEntity);
    }

    /**
     * gets user object using GitHub username
     * @param username the GitHub username
     * @return the user dto
     */
    @Override
    public UserDto getUserByUsername(String username) {
        LOGGER.info("Entering UserServiceImpl:getUserByUsername");

        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new NotFoundException("Invalid GitHub username");
        }

        return new UserDto(userEntity.getUsername(), userEntity.getAvatarUrl(), userEntity.getGithubUrl(), userEntity.getRepoUrl(), userEntity.isAdmin());
    }

    /**
     * gets user object using Token
     * @param token the Token
     * @return the user dto
     */
    @Override
    public UserDto getUserByToken(String token) {
        LOGGER.info("Entering UserServiceImpl:getUserByToken");

        UserEntity userEntity = userRepository.findByToken(token);

        if (userEntity == null) {
            throw new NotFoundException("Token not associated with any user");
        }
        return new UserDto(userEntity.getUsername(), userEntity.getAvatarUrl(), userEntity.getGithubUrl(), userEntity.getRepoUrl(), userEntity.isAdmin());
    }
}
