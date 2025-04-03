package com.my.studyrecord.domain;

import com.my.studyrecord.controller.request.UpdateMemberRequest;
import com.my.studyrecord.controller.request.UpdateStudyRequest;
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
@Table(name = "studys")
@ToString(exclude = "member")
public class Study {
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
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Study(LocalDate startDate, LocalTime startTime, int studyMins, String contents) {
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

    public void update(UpdateStudyRequest request) {
        this.startDate = request.getStartDate();
        this.startTime = request.getStartTime();
        this.studyMins = request.getStudyMins();
        this.contents = request.getContents();
    }
}
