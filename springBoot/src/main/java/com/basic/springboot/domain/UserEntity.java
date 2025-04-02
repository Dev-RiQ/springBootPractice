package com.basic.springboot.domain;

import com.basic.springboot.domain.request.UserRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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

    public void update(UserRequestDTO userRequestDTO) {
        this.email = userRequestDTO.getEmail();
    }
}
