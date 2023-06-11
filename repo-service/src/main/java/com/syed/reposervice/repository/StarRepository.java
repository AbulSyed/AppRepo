package com.syed.reposervice.repository;

import com.syed.reposervice.entity.StarredRepoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StarRepository extends JpaRepository<StarredRepoEntity, Long> {

    /*
     * query methods don't need implementation as Spring Data JPA
     * generates the implementation for them based on their method name
     */
    boolean existsByRepoIdAndStarredBy(Long repoId, String starredBy);
    List<StarredRepoEntity> findAllByRepoIdAndStarredBy(Long repoId, String starredBy);
    /*
     * implementation required as Spring Data JPA doesn't know how to
     * generate the appropriate SQL query to execute against the database
     */
    default void deleteByRepoIdAndStarredBy(Long repoId, String starredBy) {
        List<StarredRepoEntity> starredRepos = findAllByRepoIdAndStarredBy(repoId, starredBy);
        deleteAll(starredRepos);
    }
}
