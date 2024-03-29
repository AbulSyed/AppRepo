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
     * gets auth token after user login
     * @param code the authorization code sent by GitHub retrieved from url
     * @return the auth token
     */
    @Override
    public String getAuthToken(String code) {
        LOGGER.info("Entering AuthServiceImpl:getAuthToken");

        String accessToken = getAccessToken(code);
        if (accessToken != null) {
            LOGGER.info("access token is ok, auth passed");
            // we need to save token, as well as user info in db
            userService.saveOrUpdateUserToken(accessToken);
            return accessToken;
        } else {
            LOGGER.info("access token is null, auth failed");
            return null;
        }
    }

    /**
     * exchanges authorization code for access token
     * @param code the authorization code sent by GitHub retrieved from url
     * @return the access token
     */
    private String getAccessToken(String code) {
        LOGGER.info("Entering AuthServiceImpl:getAccessToken");

        String url = "https://github.com/login/oauth/access_token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add(AuthServiceConstant.CODE, code);
        data.add(AuthServiceConstant.CLIENT_ID, clientId);
        data.add(AuthServiceConstant.CLIENT_SECRET, clientSecret);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(data, headers);

        // sending auth code to GitHub to get auth token
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            // access token in the form access_token=ACCESS_TOKEN&scope=&token_type=bearer
            // we need to extract token from string
            return AuthServiceUtility.extractAccessToken(response.getBody());
        }
        return null;
    }

}
