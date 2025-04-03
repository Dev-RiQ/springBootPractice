package com.my.studyrecord.controller.response;

import com.my.studyrecord.domain.Member;
import com.my.studyrecord.domain.Study;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
public class StudyViewResponse {
    private Long id;
    private LocalDate startDate;
    private LocalTime startTime;
    private int studyMins;
    private String contents;
    private Long memberId;
    private String endDateTime;

    public StudyViewResponse (Study study) {
        this.id = study.getId();
        this.startDate = study.getStartDate();
        this.startTime = study.getStartTime();
        this.studyMins = study.getStudyMins();
        this.contents = study.getContents();
        this.memberId = study.getMember().getId();
        this.endDateTime = study.getEndStudyDay();
    }
}
