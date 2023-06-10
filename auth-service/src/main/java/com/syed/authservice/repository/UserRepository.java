package com.syed.authservice.repository;

import com.syed.authservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
    UserEntity findByToken(String token);
}
