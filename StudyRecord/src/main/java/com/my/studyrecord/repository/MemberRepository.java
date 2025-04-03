package com.my.studyrecord.repository;

import com.my.studyrecord.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
}
