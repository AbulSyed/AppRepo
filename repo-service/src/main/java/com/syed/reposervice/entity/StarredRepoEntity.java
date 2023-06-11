package com.syed.reposervice.entity;

import javax.persistence.*;

@Entity
@Table(name = "starred_repo")
public class StarredRepoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "starred_by")
    private String starredBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repo_id")
    private RepoEntity repo;

    public StarredRepoEntity() {
    }

    public StarredRepoEntity(Long id, String starredBy, RepoEntity repo) {
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

    public RepoEntity getRepo() {
        return repo;
    }

    public void setRepo(RepoEntity repo) {
        this.repo = repo;
    }

    @Override
    public String toString() {
        return "StarredRepoEntity{" +
                "id=" + id +
                ", starredBy='" + starredBy + '\'' +
                ", repo=" + repo +
                '}';
    }
}
