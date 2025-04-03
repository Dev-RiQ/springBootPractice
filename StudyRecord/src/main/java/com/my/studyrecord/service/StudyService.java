package com.my.studyrecord.service;

import com.my.studyrecord.controller.request.UpdateStudyRequest;
import com.my.studyrecord.domain.Member;
import com.my.studyrecord.domain.Study;
import com.my.studyrecord.repository.MemberRepository;
import com.my.studyrecord.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {

    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;

    public List<Study> findAll() {
        return studyRepository.findAll();
    }

    public Study findStudyById(Long id) throws Exception {
        return studyRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteAllByMemberId(Long id) {
        studyRepository.deleteAllByMemberId(id);
    }
    @Transactional
    public void insert(Long memberId, Study entity) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        entity.setMember(member);
        studyRepository.save(entity);
    }
    @Transactional
    public void update(Long id, UpdateStudyRequest request) {
        Study study = studyRepository.findById(id).orElseThrow();
        study.update(request);
        studyRepository.save(study);
    }
    @Transactional
    public void delete(Long id) {
        Study study = studyRepository.findById(id).orElseThrow();
        studyRepository.delete(study);
    }
}
