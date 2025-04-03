package com.my.studyrecord.controller;

import com.my.studyrecord.controller.request.AddMemberRequest;
import com.my.studyrecord.controller.request.AddStudyRequest;
import com.my.studyrecord.controller.request.UpdateMemberRequest;
import com.my.studyrecord.controller.request.UpdateStudyRequest;
import com.my.studyrecord.controller.response.StudyViewResponse;
import com.my.studyrecord.domain.Member;
import com.my.studyrecord.domain.Study;
import com.my.studyrecord.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping({"","/{id}"})
    public String study(@PathVariable(required = false) Long id, Model model) {
        try{
            Study study = studyService.findStudyById(id);
            model.addAttribute("study", study);
        } catch (Exception e) {
        }
        return "study/join";
    }

    @GetMapping("/list")
    public String list(Model model) {

        List<Study> list = studyService.findAll();
        List<StudyViewResponse> viewList = list.stream().map(StudyViewResponse::new).toList();
        model.addAttribute("studys", viewList);
        return "study/list";
    }

    @PostMapping({"","/"})
    public String join(AddStudyRequest request) {
        try{
            studyService.insert(request.getMemberId(), new AddStudyRequest().toEntity(request));
        } catch (Exception e) {
        }
        return "redirect:/study/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, UpdateStudyRequest request) {
        try{
            studyService.update(id, request);
        } catch (Exception e) {
        }
        return "redirect:/study/list";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        try{
            studyService.delete(id);
        } catch (Exception e) {
        }
        return "redirect:/study/list";
    }
}
