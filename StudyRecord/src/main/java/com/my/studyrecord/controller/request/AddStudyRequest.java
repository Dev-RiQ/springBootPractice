package com.my.studyrecord.controller.request;

import com.my.studyrecord.domain.Member;
import com.my.studyrecord.domain.Study;
import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddStudyRequest {
    private LocalDate startDate;
    private LocalTime startTime;
    private int studyMins;
    private String contents;
    private Long memberId;

    public Study toEntity(AddStudyRequest dto){
        return Study.builder()
                .startDate(dto.getStartDate())
                .startTime(dto.getStartTime())
                .studyMins(dto.getStudyMins())
                .contents(dto.getContents())
                .build();
    }
}
