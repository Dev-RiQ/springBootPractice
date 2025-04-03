package com.basic.springboot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@IdClass(UserAuthenticateId.class)
public class UserAuthenticateEntity {
    @Id
    private String username;
    @Id
    private String role;
    private Integer enable;

    @ManyToOne(fetch= FetchType.LAZY)
    private UserEntity user;
}
