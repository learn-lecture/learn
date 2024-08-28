package org.yeonghan.basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.yeonghan.basic.domain.Member;
import org.yeonghan.basic.repository.MemberRepository;
import org.yeonghan.basic.repository.MemoryMemberRepository;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(it -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
