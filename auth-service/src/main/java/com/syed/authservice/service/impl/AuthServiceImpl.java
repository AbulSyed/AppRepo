package com.syed.authservice.service.impl;

import com.syed.authservice.service.AuthService;
import com.syed.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService {

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
     * logins user
     * @param code the authorization code sent by GitHub retrieved from url
     * @return the users GitHub username
     */
    @Override
    public String signin(String code) {
        String accessToken = getAccessToken(code);
        System.out.println(accessToken);
        return userService.getUserByToken(accessToken);
    }

    /**
     * exchanges authorization code for access token
     * @param code the authorization code sent by GitHub retrieved from url
     * @return the access token
     */
    private String getAccessToken(String code) {
        String url = "https://github.com/login/oauth/access_token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("code", code);
        data.add("client_id", clientId);
        data.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(data, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return extractAccessToken(response.getBody());
        }
        return null;
    }

    /**
     * extracts access token from access_token=ACCESS_TOKEN&scope=&token_type=bearer
     * @param str the access token with extra params
     * @return the access token
     */
    private static String extractAccessToken(String str) {
        String[] parts = str.split("&");

        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue[0].equals("access_token")) {
                return keyValue[1];
            }
        }
        return null;
    }
}
