package com.my.studyrecord.repository;

import com.my.studyrecord.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study,Long> {
    void deleteAllByMemberId(Long id);
}
