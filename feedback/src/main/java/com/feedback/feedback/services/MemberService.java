package com.feedback.feedback.services;

import com.feedback.feedback.models.Member;
import com.feedback.feedback.repositories.IMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final IMemberRepository memberRepository;
    public MemberService(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Member findById(int id) {
        return memberRepository.findById(id);
    }

}
