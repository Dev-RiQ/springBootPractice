package com.my.studyrecordsecurity.studyRecord.controller;

import com.my.studyrecordsecurity.studyRecord.controller.request.AddStudyRecordRequest;
import com.my.studyrecordsecurity.studyRecord.controller.request.UpdateStudyRecordRequest;
import com.my.studyrecordsecurity.studyRecord.controller.response.StudyRecordViewResponse;
import com.my.studyrecordsecurity.studyRecord.domain.StudyRecord;
import com.my.studyrecordsecurity.studyRecord.service.StudyRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyRecordController {

    private final StudyRecordService studyRecordService;

    @GetMapping({"","/{id}"})
    public String study(@PathVariable(required = false) Long id, Model model) {
        try{
            model.addAttribute("curDate", LocalDate.now());
            StudyRecord studyRecord = studyRecordService.findStudyRecordById(id);
            model.addAttribute("study", studyRecord);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "study/join";
    }

    @GetMapping({"/list","/list/{userId}"})
    public String list(@PathVariable(required = false) Long userId, Model model) {
        try{
            List<StudyRecord> list = userId == null ? studyRecordService.findAll() : studyRecordService.findAllByMemberId(userId);
            List<StudyRecordViewResponse> viewList = list.stream().map(StudyRecordViewResponse::new).toList();
            model.addAttribute("studies", viewList);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "study/list";
    }

    @PostMapping({"","/"})
    public String join(AddStudyRecordRequest request) {
        try{
            studyRecordService.insert(request.getMemberId(), new AddStudyRecordRequest().toEntity(request));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/study/list";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, UpdateStudyRecordRequest request) {
        try{
            studyRecordService.update(id, request);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/study/list";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            studyRecordService.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
