package com.basic.springboot.repository;

import com.basic.springboot.domain.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByUsernameAndPassword(String username, String password);

    @EntityGraph( attributePaths = {"userAuthenticates"})
    List<UserSummary> findBy();
}
