package com.syed.reposervice.entity;

import javax.persistence.*;

@Entity
public class StarredRepo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String starredBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repo_id")
    private Repo repo;

    public StarredRepo() {
    }

    public StarredRepo(Long id, String starredBy, Repo repo) {
        this.id = id;
        this.starredBy = starredBy;
        this.repo = repo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStarredBy() {
        return starredBy;
    }

    public void setStarredBy(String starredBy) {
        this.starredBy = starredBy;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    @Override
    public String toString() {
        return "StarredRepo{" +
                "id=" + id +
                ", starredBy='" + starredBy + '\'' +
                ", repo=" + repo +
                '}';
    }
}
