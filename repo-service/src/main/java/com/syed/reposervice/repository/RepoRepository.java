package com.syed.reposervice.repository;

import com.syed.reposervice.entity.RepoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRepository extends JpaRepository<RepoEntity, Long> {
}
