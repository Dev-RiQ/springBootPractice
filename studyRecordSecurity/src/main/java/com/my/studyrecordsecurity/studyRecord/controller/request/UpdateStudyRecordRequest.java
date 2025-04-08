package com.my.studyrecordsecurity.studyRecord.controller.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateStudyRecordRequest {
    private LocalDate startDate;
    private LocalTime startTime;
    private int studyMins;
    private String contents;
}
