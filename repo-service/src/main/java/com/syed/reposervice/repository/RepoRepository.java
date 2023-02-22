package com.syed.reposervice.repository;

import com.syed.reposervice.entity.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRepository extends JpaRepository<Repo, Long> {
}
