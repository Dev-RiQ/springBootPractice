package com.my.security3.repository;

import com.my.security3.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByUserId(String username);

    UserEntity findByUserId(String userId);
}