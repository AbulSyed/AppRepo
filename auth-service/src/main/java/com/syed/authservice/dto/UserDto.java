package com.syed.authservice.dto;

public class UserDto {

    private String login;
    private String avatar_url;
    private String html_url;
    private String repos_url;
    private boolean isAdmin;

    public UserDto() {
    }

    public UserDto(String login, String avatar_url, String html_url, String repos_url, boolean isAdmin) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.html_url = html_url;
        this.repos_url = repos_url;
        this.isAdmin = isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
