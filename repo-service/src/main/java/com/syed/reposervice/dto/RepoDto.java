package com.syed.reposervice.dto;

import com.syed.reposervice.enumeration.CategoryEnum;

import java.util.Set;

public class RepoDto {

    private Long id;
    private String name;
    private String description;
    private String html_url;
    private String language;
    private String clone_url;
    private CategoryEnum category;
    private Set<String> tech;

    public RepoDto() {
    }

    public RepoDto(Long id, String name, String description, String html_url, String language, String clone_url, CategoryEnum category, Set<String> tech) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.html_url = html_url;
        this.language = language;
        this.clone_url = clone_url;
        this.category = category;
        this.tech = tech;
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

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
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

    @Override
    public String toString() {
        return "RepoDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", html_url='" + html_url + '\'' +
                ", language='" + language + '\'' +
                ", clone_url='" + clone_url + '\'' +
                ", category=" + category +
                ", tech=" + tech +
                '}';
    }
}
