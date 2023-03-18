package com.syed.reposervice.dto;

public class StarredRepoDto {

    private Long repoId;
    private String starredBy;

    public StarredRepoDto() {
    }

    public StarredRepoDto(Long repoId, String starredBy) {
        this.repoId = repoId;
        this.starredBy = starredBy;
    }

    public Long getRepoId() {
        return repoId;
    }

    public void setRepoId(Long repoId) {
        this.repoId = repoId;
    }

    public String getStarredBy() {
        return starredBy;
    }

    public void setStarredBy(String starredBy) {
        this.starredBy = starredBy;
    }

    @Override
    public String toString() {
        return "StarredRepoDto{" +
                "repoId=" + repoId +
                ", starredBy='" + starredBy + '\'' +
                '}';
    }
}
