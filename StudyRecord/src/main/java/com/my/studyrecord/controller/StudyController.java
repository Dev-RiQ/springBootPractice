package com.my.studyrecord.controller;

import com.my.studyrecord.controller.request.AddMemberRequest;
import com.my.studyrecord.controller.request.AddStudyRequest;
import com.my.studyrecord.controller.request.UpdateMemberRequest;
import com.my.studyrecord.controller.request.UpdateStudyRequest;
import com.my.studyrecord.controller.response.StudyViewResponse;
import com.my.studyrecord.domain.Member;
import com.my.studyrecord.domain.Study;
import com.my.studyrecord.service.MemberService;
import com.my.studyrecord.service.StudyService;
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
public class StudyController {

    private final StudyService studyService;

    @GetMapping({"","/{id}"})
    public String study(@PathVariable(required = false) Long id, Model model) {
        try{
            model.addAttribute("curDate", LocalDate.now());
            Study study = studyService.findStudyById(id);
            model.addAttribute("study", study);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "study/join";
    }

    @GetMapping({"/list","/list/{memberId}"})
    public String list(@PathVariable(required = false) Long memberId, Model model) {
        try{
            List<Study> list = memberId == null ? studyService.findAll() : studyService.findAllByMemberId(memberId);
            List<StudyViewResponse> viewList = list.stream().map(StudyViewResponse::new).toList();
            model.addAttribute("studys", viewList);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "study/list";
    }

    @PostMapping({"","/"})
    public String join(AddStudyRequest request) {
        try{
            studyService.insert(request.getMemberId(), new AddStudyRequest().toEntity(request));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/study/list";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, UpdateStudyRequest request) {
        try{
            studyService.update(id, request);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/study/list";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            studyService.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
