package com.basic.springboot.domain;

import com.basic.springboot.domain.request.UserRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    private String username;
    @Column(nullable=false)
    private String password;
    @Column(unique=true, nullable=false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserAuthenticateEntity> userAuthenticates = new ArrayList<>();

    public void update(UserRequestDTO userRequestDTO) {
        this.email = userRequestDTO.getEmail();
    }
}
