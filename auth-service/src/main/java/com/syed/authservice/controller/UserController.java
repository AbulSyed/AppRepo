package com.syed.authservice.controller;

import com.syed.authservice.dto.TokenDto;
import com.syed.authservice.dto.UserDto;
import com.syed.authservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Comment controller exposes endpoints: getUser, getUser")
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
    @ApiOperation(value = "Endpoint which gets user using GitHub username")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "OK"
            )
    })
    @ResponseStatus(HttpStatus.OK)
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
    @ApiOperation(value = "Endpoint which gets user using a token")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "OK"
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getUser")
    public UserDto getUserByToken(@RequestBody TokenDto token) {
        LOGGER.info("Entering UserController:getUserByToken");

        return userService.getUserByToken(token.getToken());
    }
}
