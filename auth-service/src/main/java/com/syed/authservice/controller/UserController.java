package com.syed.authservice.controller;

import com.syed.authservice.dto.TokenDto;
import com.syed.authservice.dto.UserDto;
import com.syed.authservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * returns user dto using the GitHub username
     * @param username the GitHub username
     * @return the user dto
     */
    @GetMapping("/getUser/{username}")
    public UserDto getUser(@PathVariable String username) {
        LOGGER.info("Entering UserController:getUser");

        return userService.getUserByUsername(username);
    }

    /**
     * returns user dto using the Token
     * @param token the users Token
     * @return the user dto
     */
    @PostMapping("/getUser")
    public UserDto getUserByToken(@RequestBody TokenDto token) {
        LOGGER.info("Entering UserController:getUserByToken");

        return userService.getUserByToken(token.getToken());
    }
}
