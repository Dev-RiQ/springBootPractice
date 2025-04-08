package com.my.studyrecordsecurity.studyRecord.domain;

import com.my.studyrecordsecurity.studyRecord.controller.request.UpdateStudyRecordRequest;
import com.my.studyrecordsecurity.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "studies")
@ToString(exclude = "user")
public class StudyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;
    private LocalDate startDate;
    private LocalTime startTime;
    private int studyMins;
    @Lob
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public StudyRecord(LocalDate startDate, LocalTime startTime, int studyMins, String contents) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.studyMins = studyMins;
        this.contents = contents;
    }

    public String getEndStudyDay(){
        String pattern = "yyyy-MM-dd HH:mm";
        LocalDateTime endDateTime = LocalDateTime.of(this.startDate, this.startTime);
        endDateTime = endDateTime.plusMinutes(this.studyMins);
        return endDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public void update(UpdateStudyRecordRequest request) {
        this.startDate = request.getStartDate();
        this.startTime = request.getStartTime();
        this.studyMins = request.getStudyMins();
        this.contents = request.getContents();
    }
}
