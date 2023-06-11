package com.syed.reposervice.entity;

import com.syed.reposervice.enumeration.CategoryEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "repo")
public class RepoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "html_url", nullable = false)
    private String htmlUrl;

    @Column(name = "language")
    private String language;

    @Column(name = "clone_url", nullable = false)
    private String cloneUrl;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    // @ElementCollection annotation indicates that the tech field is a
    // collection of simple values that should be stored in a separate table.
    // JPA will create a new table to store the tech values associated with each Repo entity.
    // The new table will have a foreign key to the Repo table.
    @ElementCollection
    @CollectionTable(name = "repo_tech", joinColumns = @JoinColumn(name = "repo_id"))
    @Column(name = "tech")
    private Set<String> tech;

    @OneToMany(mappedBy = "repo", cascade = CascadeType.ALL)
    private Set<StarredRepoEntity> starredRepoEntity;

    public RepoEntity() {
    }

    public RepoEntity(Long id, String name, String description, String htmlUrl, String language, String cloneUrl, CategoryEnum category, Set<String> tech, Set<StarredRepoEntity> starredRepoEntity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.htmlUrl = htmlUrl;
        this.language = language;
        this.cloneUrl = cloneUrl;
        this.category = category;
        this.tech = tech;
        this.starredRepoEntity = starredRepoEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public Set<String> getTech() {
        return tech;
    }

    public void setTech(Set<String> tech) {
        this.tech = tech;
    }

    public Set<StarredRepoEntity> getStarredRepo() {
        return starredRepoEntity;
    }

    public void setStarredRepo(Set<StarredRepoEntity> starredRepoEntity) {
        this.starredRepoEntity = starredRepoEntity;
    }

//    @Override
//    public String toString() {
//        return "Repo{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", htmlUrl='" + htmlUrl + '\'' +
//                ", language='" + language + '\'' +
//                ", cloneUrl='" + cloneUrl + '\'' +
//                ", category=" + category +
//                ", tech=" + tech +
//                ", starredRepo=" + starredRepo +
//                '}';
//    }
}
