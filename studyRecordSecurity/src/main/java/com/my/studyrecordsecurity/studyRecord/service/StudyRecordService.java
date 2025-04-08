package com.my.studyrecordsecurity.studyRecord.service;

import com.my.studyrecordsecurity.studyRecord.controller.request.UpdateStudyRecordRequest;
import com.my.studyrecordsecurity.studyRecord.domain.StudyRecord;
import com.my.studyrecordsecurity.studyRecord.repository.StudyRecordRepository;
import com.my.studyrecordsecurity.user.domain.User;
import com.my.studyrecordsecurity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyRecordService {

    private final UserRepository userRepository;
    private final StudyRecordRepository studyRecordRepository;

    public List<StudyRecord> findAll() {
        List<StudyRecord> studies = studyRecordRepository.findAll();
        if(studies.isEmpty()){
            throw new NoSuchElementException();
        }
        return studies;
    }

    public List<StudyRecord> findAllByMemberId(Long memberId) {
        List<StudyRecord> studies = studyRecordRepository.findAllByUserId(memberId);
        if(studies.isEmpty()){
            throw new NoSuchElementException();
        }
        return studies;
    }

    public StudyRecord findStudyRecordById(Long id) throws NoSuchElementException {
        return studyRecordRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteAllByUserId(Long id) throws NoSuchElementException {
        if(!userRepository.existsById(id)){
            throw new NoSuchElementException();
        }
        studyRecordRepository.deleteAllByUserId(id);
    }
    @Transactional
    public void insert(Long userId, StudyRecord entity) throws NoSuchElementException {
        User user = userRepository.findById(userId).orElseThrow();
        entity.setUser(user);
        studyRecordRepository.save(entity);
    }
    @Transactional
    public void update(Long id, UpdateStudyRecordRequest request) throws NoSuchElementException {
        StudyRecord studyRecord = studyRecordRepository.findById(id).orElseThrow();
        studyRecord.update(request);
        studyRecordRepository.save(studyRecord);
    }
    @Transactional
    public void delete(Long id) throws NoSuchElementException {
        StudyRecord studyRecord = studyRecordRepository.findById(id).orElseThrow();
        studyRecordRepository.delete(studyRecord);
    }
}
