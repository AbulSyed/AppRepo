package com.syed.authservice.service.impl;

import com.syed.authservice.service.AuthService;
import com.syed.authservice.service.UserService;
import com.syed.authservice.utility.AuthServiceConstant;
import com.syed.authservice.utility.AuthServiceUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Value("${github.clientId}")
    private String clientId;
    @Value("${github.clientSecret}")
    private String clientSecret;

    private final RestTemplate restTemplate;
    private final UserService userService;

    public AuthServiceImpl(RestTemplate restTemplate, UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    /**
     * gets the logged-in user GitHub username
     * @param code the authorization code sent by GitHub retrieved from url
     * @return the users GitHub username
     */
    @Override
    public String getUsername(String code) {
        LOGGER.debug("Entering AuthServiceImpl:getUsername");

        String accessToken = getAccessToken(code);
        if (accessToken != null) {
            LOGGER.debug("access token is ok, auth passed");
            return userService.getUserByToken(accessToken);
        } else {
            LOGGER.debug("access token is null, auth failed");
            return null;
        }
    }

    /**
     * exchanges authorization code for access token
     * @param code the authorization code sent by GitHub retrieved from url
     * @return the access token
     */
    private String getAccessToken(String code) {
        LOGGER.debug("Entering AuthServiceImpl:getAccessToken");

        String url = "https://github.com/login/oauth/access_token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add(AuthServiceConstant.CODE, code);
        data.add(AuthServiceConstant.CLIENT_ID, clientId);
        data.add(AuthServiceConstant.CLIENT_SECRET, clientSecret);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(data, headers);

//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return AuthServiceUtility.extractAccessToken(response.getBody());
        }
        return null;
    }

}
