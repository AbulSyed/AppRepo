package com.syed.authservice.controller;

import com.syed.authservice.service.AuthService;
import com.syed.authservice.utility.AuthServiceUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * endpoint invoked by GitHub
     * gets authorization code from url and uses to fetch and save user to database
     * cookie sent to browser with username to then be used to make request to UserController
     * @param code the authorization code sent by GitHub in url
     * @param response the HttpServletResponse containing cookie
     * @return the redirection to the React homepage after authentication
     */
    @GetMapping("/user/signin/callback")
    public RedirectView callback(@RequestParam(value = "code") String code,
                               HttpServletResponse response) {
        LOGGER.debug("Entering AuthController:signin");

        String name = authService.signin(code);

        if (name != null) {
            Cookie cookie = AuthServiceUtility.createCookie(
                    "loggedIn", name, 60000, false, "localhost", "/");
            response.addCookie(cookie);

            return new RedirectView("http://localhost:3000/");
        } else {
            return new RedirectView("http://localhost:3000/500");
        }
    }
}
