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
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {

    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;

    public List<Study> findAll() {
        List<Study> studies = studyRepository.findAll();
        if(studies.isEmpty()){
            throw new NoSuchElementException();
        }
        return studies;
    }

    public List<Study> findAllByMemberId(Long memberId) {
        List<Study> studies = studyRepository.findAllByMemberId(memberId);
        if(studies.isEmpty()){
            throw new NoSuchElementException();
        }
        return studies;
    }

    public Study findStudyById(Long id) throws NoSuchElementException {
        return studyRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteAllByMemberId(Long id) throws NoSuchElementException {
        if(!memberRepository.existsById(id)){
            throw new NoSuchElementException();
        }
        studyRepository.deleteAllByMemberId(id);
    }
    @Transactional
    public void insert(Long memberId, Study entity) throws NoSuchElementException {
        Member member = memberRepository.findById(memberId).orElseThrow();
        entity.setMember(member);
        studyRepository.save(entity);
    }
    @Transactional
    public void update(Long id, UpdateStudyRequest request) throws NoSuchElementException {
        Study study = studyRepository.findById(id).orElseThrow();
        study.update(request);
        studyRepository.save(study);
    }
    @Transactional
    public void delete(Long id) throws NoSuchElementException {
        Study study = studyRepository.findById(id).orElseThrow();
        studyRepository.delete(study);
    }

}
