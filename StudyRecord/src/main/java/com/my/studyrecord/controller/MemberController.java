package com.my.studyrecord.controller;

import com.my.studyrecord.controller.request.AddMemberRequest;
import com.my.studyrecord.controller.request.UpdateMemberRequest;
import com.my.studyrecord.controller.response.MemberViewResponse;
import com.my.studyrecord.domain.Member;
import com.my.studyrecord.service.MemberService;
import com.my.studyrecord.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final StudyService studyService;

    @GetMapping({"","/{id}"})
    public String member(@PathVariable(required = false) Long id, Model model) {
        try{
            Member member = memberService.findMemberById(id);
            model.addAttribute("member", member);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "member/join";
    }

    @GetMapping("/list")
    public String list(Model model) {
        try{
            List<Member> members = memberService.findAll();
            List<MemberViewResponse> memberViewResponses = members.stream().map(MemberViewResponse::new).toList();
            model.addAttribute("members",memberViewResponses);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "member/list";
    }

    @PostMapping({"","/"})
    public String join(AddMemberRequest request) {
        memberService.insert(new AddMemberRequest().toEntity(request));
        return "redirect:/member/list";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, UpdateMemberRequest request) {
        try{
            memberService.update(id, request);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/member/list";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            studyService.deleteAllByMemberId(id);
            memberService.delete(id);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
