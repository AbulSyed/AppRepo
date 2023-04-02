package com.syed.authservice.service;

import com.syed.authservice.dto.UserDto;

public interface UserService {

//    String getUserByToken(String accessToken);
//    void saveUser(UserDto userDto, String accessToken);
//    void updateUserToken(String username, String accessToken);
    UserDto getUserByUsername(String username);
    void saveOrUpdateUserToken(String token);
    UserDto getUserByToken(String token);
}
