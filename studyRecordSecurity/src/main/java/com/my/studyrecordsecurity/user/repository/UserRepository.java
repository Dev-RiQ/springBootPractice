package com.my.studyrecordsecurity.user.repository;

import com.my.studyrecordsecurity.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<Object> findByUsernameAndEmailAndName(String providerId, String email, String name);
}