package com.syed.authservice.controller;

import com.syed.authservice.service.AuthService;
import com.syed.authservice.utility.AuthServiceUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Api(value = "Auth controller exposes a callback endpoint")
@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * endpoint invoked by GitHub after user submit login credentials, GitHub sends authorization code
     * method gets authorization code from url and uses to fetch and save user to database
     * cookie sent to browser with token to then be used to make request to UserController
     * @param code the authorization code sent by GitHub in url
     * @param response the HttpServletResponse containing cookie
     * @return the redirection to the React homepage after authentication
     */
    @ApiOperation(value = "Callback endpoint invoked by GitHub after a user login, GitHub sends authorization code, which is used to fetch user info")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "OK"
            )
    })
    @GetMapping("/user/signin/callback")
    public RedirectView callback(@RequestParam(value = "code") String code,
                               HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Entering AuthController:callback");

        String token = authService.getAuthToken(code);

        if (token != null) {
            // sending auth token in cookie
            Cookie cookie = AuthServiceUtility.createCookie(
                    "token", token, 60000, false, "localhost", "/");
            response.addCookie(cookie);

            long endTime = System.currentTimeMillis();
            AuthServiceUtility.calcTimeTaken("AuthController:callback time taken: {} ms", startTime, endTime);
            return new RedirectView("http://localhost:3000/");
        } else {
            long endTime = System.currentTimeMillis();
            AuthServiceUtility.calcTimeTaken("AuthController:callback time taken: {} ms", startTime, endTime);
            return new RedirectView("http://localhost:3000/500");
        }
    }
}
