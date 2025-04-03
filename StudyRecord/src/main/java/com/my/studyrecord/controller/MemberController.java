package com.my.studyrecord.controller;

import com.my.studyrecord.controller.request.AddMemberRequest;
import com.my.studyrecord.controller.request.UpdateMemberRequest;
import com.my.studyrecord.controller.response.MemberViewResponse;
import com.my.studyrecord.domain.Member;
import com.my.studyrecord.service.MemberService;
import com.my.studyrecord.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        }
        return "member/join";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Member> members = memberService.findAll();
        List<MemberViewResponse> memberViewResponses = members.stream().map(MemberViewResponse::new).toList();
        model.addAttribute("members",memberViewResponses);
        return "member/list";
    }

    @PostMapping({"","/"})
    public String join(AddMemberRequest request) {
        memberService.insert(new AddMemberRequest().toEntity(request));
        return "redirect:/member/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, UpdateMemberRequest request) {
        memberService.update(id, request);
        return "redirect:/member/list";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        studyService.deleteAllByMemberId(id);
        memberService.delete(id);
        return "redirect:/member/list";
    }
}
