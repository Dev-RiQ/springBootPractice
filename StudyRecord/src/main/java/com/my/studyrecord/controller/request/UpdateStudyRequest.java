package com.my.studyrecord.controller.request;

import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateStudyRequest {
    private LocalDate startDate;
    private LocalTime startTime;
    private int studyMins;
    private String contents;
}
