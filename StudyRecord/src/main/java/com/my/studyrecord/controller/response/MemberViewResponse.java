package com.my.studyrecord.controller.response;

import com.my.studyrecord.domain.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberViewResponse {
    private Long id;
    private String loginId;
    private String name;
    private String role;

    public MemberViewResponse (Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.role = member.getRole().toString();
    }
}
