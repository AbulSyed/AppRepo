package com.syed.reposervice.dto;

public class UsernameDto {

    private String username;

    public UsernameDto() {
    }

    public UsernameDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "UsernameDto{" +
                "username='" + username + '\'' +
                '}';
    }
}
