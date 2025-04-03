package com.my.studyrecord.controller.request;

import com.my.studyrecord.domain.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddMemberRequest {
    private String loginId;
    private String password;
    private String name;

    public Member toEntity(AddMemberRequest dto){
        return Member.builder()
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .name(dto.getName())
                .build();
    }
}
