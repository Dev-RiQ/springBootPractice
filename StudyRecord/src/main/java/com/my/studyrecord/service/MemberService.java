package com.my.studyrecord.service;

import com.my.studyrecord.controller.request.UpdateMemberRequest;
import com.my.studyrecord.domain.Member;
import com.my.studyrecord.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findMemberById(Long id) throws Exception {
        return memberRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void insert(Member entity) {
        memberRepository.save(entity);
    }

    @Transactional
    public void update(Long id, UpdateMemberRequest request) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.update(request);
        memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(member);
    }
}
