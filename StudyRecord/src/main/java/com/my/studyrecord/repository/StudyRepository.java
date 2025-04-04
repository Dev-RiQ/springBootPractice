package com.my.studyrecord.repository;

import com.my.studyrecord.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study,Long> {
    void deleteAllByMemberId(Long id);
    List<Study> findAllByMemberId(Long id);
}
