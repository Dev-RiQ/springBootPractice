package com.my.studyrecordsecurity.studyRecord.controller;

import com.my.studyrecordsecurity.studyRecord.controller.request.AddStudyRecordRequest;
import com.my.studyrecordsecurity.studyRecord.controller.request.UpdateStudyRecordRequest;
import com.my.studyrecordsecurity.studyRecord.controller.response.StudyRecordViewResponse;
import com.my.studyrecordsecurity.studyRecord.domain.StudyRecord;
import com.my.studyrecordsecurity.studyRecord.service.StudyRecordService;
import com.my.studyrecordsecurity.user.domain.User;
import com.my.studyrecordsecurity.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserService userService;

    @GetMapping({"","/{id}"})
    public String study(@PathVariable(required = false) Long id, Model model) {
        try{
            model.addAttribute("curDate", LocalDate.now());
            if(id == null){return "study/join";}
            StudyRecord studyRecord = studyRecordService.findStudyRecordById(id);
            model.addAttribute("study", studyRecord);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "study/join";
    }

    @GetMapping({"/list","/list/{searchUserId}"})
    public String list(@PathVariable(required = false) Long searchUserId, Model model) {
        try{
            List<StudyRecord> list = searchUserId == null ? studyRecordService.findAll() : studyRecordService.findAllByMemberId(searchUserId);
            List<StudyRecordViewResponse> viewList = list.stream().map(StudyRecordViewResponse::new).toList();
            model.addAttribute("studies", viewList);
            User loginUser = userService.getLoginUser();
            model.addAttribute("userId",loginUser.getId());
            model.addAttribute("userRole",loginUser.getRole().toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "study/list";
    }

    @PostMapping({"","/"})
    public String join(AddStudyRecordRequest request) {
        try{
            studyRecordService.insert(new AddStudyRecordRequest().toEntity(request));
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
