package com.my.studyrecord.controller.request;

import com.my.studyrecord.domain.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateMemberRequest {
    private String password;
    private String name;
}
