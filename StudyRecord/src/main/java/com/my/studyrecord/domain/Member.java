package com.my.studyrecord.domain;

import com.my.studyrecord.controller.request.UpdateMemberRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(updatable = false, unique = true, nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = Role.STUDENT;
    }

    public void update(UpdateMemberRequest request) {
        this.password = request.getPassword();
        this.name = request.getName();
    }
}
