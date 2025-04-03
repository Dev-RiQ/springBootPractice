package com.basic.springboot.repository;

import com.basic.springboot.domain.UserAuthenticateEntity;
import com.basic.springboot.domain.UserAuthenticateId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthenticateRepository extends JpaRepository<UserAuthenticateEntity, UserAuthenticateId> {

}
