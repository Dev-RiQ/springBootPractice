package com.my.studyrecord.service;

import com.my.studyrecord.controller.request.UpdateMemberRequest;
import com.my.studyrecord.domain.Member;
import com.my.studyrecord.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll() throws NoSuchElementException{
        List<Member> members = memberRepository.findAll();
        if(members.isEmpty()){
            throw new NoSuchElementException();
        }
        return members;
    }

    public Member findMemberById(Long id) throws NoSuchElementException {
        return memberRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void insert(Member entity) {
        memberRepository.save(entity);
    }

    @Transactional
    public void update(Long id, UpdateMemberRequest request) throws NoSuchElementException {
        Member member = memberRepository.findById(id).orElseThrow();
        member.update(request);
        memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) throws NoSuchElementException {
        Member member = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(member);
    }
}
