package com.my.studyrecordsecurity.user.repository;

import com.my.studyrecordsecurity.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}