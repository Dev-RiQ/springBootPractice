package com.my.studyrecordsecurity.user.domain;

import com.my.studyrecordsecurity.security.userSecurity.dto.UserPasswordEncoder;
import com.my.studyrecordsecurity.user.controller.request.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(updatable = false, unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Type type;

    @Builder
    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = new UserPasswordEncoder(password).getPassword();
        this.name = name;
        this.email = email;
        this.role = Role.ROLE_USER;
        this.type = Type.NONE;
    }

    public void update(UpdateUserRequest request) {
        this.password = new UserPasswordEncoder(request.getPassword()).getPassword();
        this.name = request.getName();
    }
}
