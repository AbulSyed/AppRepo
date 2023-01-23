package com.syed.authservice.controller;

import com.syed.authservice.dto.UserDto;
import com.syed.authservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @CrossOrigin
    @GetMapping("/{username}")
    public UserDto loggedIn(@PathVariable String username) {
        LOGGER.debug("Entering UserController:loggedIn");

        return userService.getUserByUsername(username);
    }
}
