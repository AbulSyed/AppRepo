package com.syed.authservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "github_url", nullable = false)
    private String githubUrl;

    @Column(name = "repo_url", nullable = false)
    private String repoUrl;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "is_admin")
    private boolean isAdmin;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String avatarUrl, String githubUrl, String repoUrl, String token, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.githubUrl = githubUrl;
        this.repoUrl = repoUrl;
        this.token = token;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}