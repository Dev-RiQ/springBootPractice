package com.my.studyrecordsecurity.studyRecord.repository;

import com.my.studyrecordsecurity.studyRecord.domain.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyRecordRepository extends JpaRepository<StudyRecord, Long> {
    void deleteAllByUserId(Long id);
    List<StudyRecord> findAllByUserId(Long id);
}
