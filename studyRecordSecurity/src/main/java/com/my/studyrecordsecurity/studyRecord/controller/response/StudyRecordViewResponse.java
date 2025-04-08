package com.my.studyrecordsecurity.studyRecord.controller.response;

import com.my.studyrecordsecurity.studyRecord.domain.StudyRecord;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
public class StudyRecordViewResponse {
    private Long id;
    private LocalDate startDate;
    private LocalTime startTime;
    private int studyMins;
    private String contents;
    private String userName;
    private String endDateTime;

    public StudyRecordViewResponse(StudyRecord studyRecord) {
        this.id = studyRecord.getId();
        this.startDate = studyRecord.getStartDate();
        this.startTime = studyRecord.getStartTime();
        this.studyMins = studyRecord.getStudyMins();
        this.contents = studyRecord.getContents();
        this.userName = studyRecord.getUser().getUsername();
        this.endDateTime = studyRecord.getEndStudyDay();
    }
}
