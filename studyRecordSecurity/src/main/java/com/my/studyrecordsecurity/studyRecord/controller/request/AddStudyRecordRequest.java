package com.my.studyrecordsecurity.studyRecord.controller.request;

import com.my.studyrecordsecurity.studyRecord.domain.StudyRecord;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddStudyRecordRequest {
    private LocalDate startDate;
    private LocalTime startTime;
    private int studyMins;
    private String contents;
    private Long memberId;

    public StudyRecord toEntity(AddStudyRecordRequest dto){
        return StudyRecord.builder()
                .startDate(dto.getStartDate())
                .startTime(dto.getStartTime())
                .studyMins(dto.getStudyMins())
                .contents(dto.getContents())
                .build();
    }
}
